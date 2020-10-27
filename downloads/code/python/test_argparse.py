import argparse

if __name__ == "__main__":
    # Creating a parser
    parser = argparse.ArgumentParser(
        prog='myprogram',
        description='A brief description about your program.',
        epilog='A brief epilog about your program.')

    # Adding a simple positional argument.
    parser.add_argument('echo', help="echo the string you use here", default='hello world')
    
    # Adding a optional argument with both the short and long format of the name. 
    parser.add_argument('-v', '--verbosity', help='Display some details.')

    # Adding a argument with a const value or none if not specified. 
    parser.add_argument('--flag', action='store_const', const=42, help='Set flag to 42 if specified, otherwise remains none.')
    
    # Enable or disable some feature, the value of this feature will be false initially, 
    # because the second store_false action sets the default value of this attribute to false.
    parser.add_argument('--feature', dest='feature', action='store_true', help='Enable this feature.')
    parser.add_argument('--no-feature', dest='feature', action='store_false', help='Disable this feature.')
    
    # Adding a argument which can be set twice and each value will be appended.
    parser.add_argument('--append', action='append')

    # Adding a argument with count action
    parser.add_argument('--counter', '-c', action='count')

    # Adding version argument
    parser.add_argument('--version', action='version', version='%(prog)s 1.0')

    # Adding a argument with more values
    parser.add_argument('--one', nargs=1)
    parser.add_argument('--two', nargs=2)
    parser.add_argument('--zero-or-one', nargs='?', const='c', default='d')
    parser.add_argument('--zero-to-more', nargs='*')
    parser.add_argument('--one-to-more', nargs='+')
    parser.add_argument('--other', nargs=argparse.REMAINDER)

    # Add a argument with a default value (of the specified type).
    parser.add_argument('pos-default1', nargs='?', default='positional1')
    parser.add_argument('pos-default2', nargs='*', default='positional2')
    parser.add_argument('--none-default')
    parser.add_argument('--any-default', default='42')
    parser.add_argument('--int-default', default=42, type=int)
    parser.add_argument('--float-default', default=42.5, type=float)
    parser.add_argument('--file-default', default='test_argparse.py', type=open)
    parser.add_argument('--sup-default', default=argparse.SUPPRESS)

    # Adding a argument with some choices.
    parser.add_argument('choices', choices=['left', 'mid', 'right'])
    parser.add_argument('--int-choice', type=int, choices=range(1, 4))

    # Parsing arguments
    args = parser.parse_args()

    # Dispaly args and its type.
    print('----------------------- Args ------------------------------')
    print(type(args))
    print(args)

    # Display all args line by line.
    print('----------------------- More details ------------------------------')
    args_dict = args.__dict__
    for arg in args_dict.keys():
        print(arg, '=', args_dict[arg], ', type:', type(args_dict[arg]))
    
    # ... do something with args[.ARGS] ...