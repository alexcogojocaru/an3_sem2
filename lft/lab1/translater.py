output_translated = open('output.py', 'wb')
declared_variables = []
inside_block = ''

class LineFormat:
    def process_variable(self, line):
        line = line.replace('__var', '')
        line = line.replace('\n', '')
        line = line.replace(' ', '')
        line = line.split(',')
        print(line)
        for var in line:
            declared_variables.append(var[0])
            output_translated.write(f'{inside_block}{str().join(var)}\n'.encode())

    def process_loop(self, line):
        global inside_block

        line = line.replace('__for', '')
        line = line.replace('\n', '')
        line = line.split(' ')[1:]
        if len(line) != 4:
            raise ('error invalid number of arguments')
        else:
            variable = line[0]
            start = line[1]
            stop = line[2]
            step = line[3]

            for_translated = f'for {variable} in range({start}, {stop}, {step}):'
            output_translated.write(f'{inside_block}{for_translated}\n'.encode())

            inside_block += '\t'

    def process_print(self, line):
        line = line.replace('__print', '')
        line = line.replace('\n', '')
        line = line.replace(' ', '')
        output_translated.write(f'{inside_block}print(\''.encode())
        for var in line:
            output_translated.write(f'{var}'.encode())
        output_translated.write('\')\n'.encode())

    def process_if(self, line):
        pass

reserved_keywords = ['for']

line_format = LineFormat()

with open('pseudocode.txt', 'r') as pseudo_file:
    for line in pseudo_file.readlines():
        if line.find('__var') != -1:
            line_format.process_variable(line)
        elif line.find('__for') != -1:
            line_format.process_loop(line)
        elif line.find('__endfor') != -1:
            inside_block = inside_block[0: len(inside_block) - 2]
        elif line.find('__print') != -1:
            line_format.process_print(line)
        elif line.find('__if') != -1:
            line_format.process_if(line)
print(declared_variables)

# Declarare de variabile
#     var x = 3 => x = 3 (in python)
#
# Instructiuni
#   for variable start stop step => for variable in range(start, stop, step):