global increase

section .text
increase:
mov rax, [rdi]

retry:
mov rsi, rax
inc rsi
lock
cmpxchg [rdi], rsi
;inc dword [rdi]
dec rsi
cmp rax, rsi
jnz retry
inc rax

ret
