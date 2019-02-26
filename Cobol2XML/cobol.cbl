identification division.
program-id.  base.


date-written.  07-mar-1995 - mb.

remarks.

     This program convert a value of a generic system base to a numeric value
     and viceversa.

data division.
working-storage section.

77  ind                                 pic 99 comp-x.
77  rest_divide                         pic 99.

01  w_number                            pic 9(16).
01  entry_number                        pic 9(16).
01  entry_char   redefines entry_number pic x(16).

01  current_base                        pic 9(2).
    88  base_2                          value 2.
    88  base_8                          value 8.
    88  base_10                         value 10.
    88  base_16                         value 16.

01  hex_dec_data                        pic x(48) value
    "000101202303404505606707808909A10B11C12D13E14F15".

01  hex_decimal_table redefines hex_dec_data.
    03  hex_table
        occurs 16 times
        ascending key is hex_value, dec_value
        indexed by hex_idx.
        05  hex_value                   pic x.
        05  dec_value                   pic 99.

procedure division.
main-logic.

    display window erase

    display "Base:  " no
    accept current_base convert
    display "Value: " no
    accept entry_char

    perform base-to-decimal thru base-to-decimal-ex

    display "Decimal  value: " entry_char

    perform decimal-to-base thru decimal-to-base-ex

    display "Base: " current_base " value: " entry_char

    accept omitted

    goback.

***---
***---  convert from decimal to base system
***---
decimal-to-base.
    move entry_number  to w_number
    move spaces        to entry_char
    move 16    to ind
    perform until w_number < current_base
       divide current_base into w_number giving w_number
              remainder rest_divide
       end-divide

       search all hex_table
          at end
               continue
          when dec_value( hex_idx ) is = rest_divide
               move hex_value( hex_idx) to entry_char(ind:1)

       end_search

       subtract 1 from ind
    end-perform.
    if w_number not = 0

       search all hex_table
          at end
               continue
          when dec_value( hex_idx ) is = w_number
               move hex_value( hex_idx) to entry_char(ind:1)

       end_search

    end-if.
decimal-to-base-ex.

***---
***---  convert from base to dicimal system
***---
base-to-decimal.

***--- allineamento a destra della variabile entry_char (ver 2.3.1 o sup)
    call "c$justify" using entry_char "R".

    call "c$toupper" using entry_char, value 16.

    move 0     to w_number rest_divide
    perform test after varying ind from 1 by 1 until ind = 16

       search all hex_table
          at end
               continue
          when hex_value( hex_idx ) is = entry_char(ind:1)
               move dec_value( hex_idx) to rest_divide

       end_search

       compute w_number = w_number + rest_divide * current_base ** (16 - ind)

    end-perform.

    move w_number to entry_number.

base-to-decimal-ex.
