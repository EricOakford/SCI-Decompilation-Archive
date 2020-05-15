;;; Sierra Script 1.0 - (do not remove this comment)
(script# DYING)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Motion)
(use System)

(public
	DyingScript 0
)

;;;(instance DyingScript of Script
;;;	(properties)
;;;	
;;;	(method (changeState newState &tmp [buf 133])
;;;		(asm
;;;			lap      newState
;;;			aTop     state
;;;			push    
;;;			dup     
;;;			ldi      0
;;;			eq?     
;;;			bnt      code_0042
;;;			pushi    0
;;;			callb    HandsOff,  0
;;;			pushi    #fade
;;;			pushi    0
;;;			lag      music
;;;			send     4
;;;			pushi    #setScript
;;;			pushi    1
;;;			pushi    0
;;;			lag      curRoom
;;;			send     6
;;;			ldi      egoDEATHMESSAGE
;;;			sag      currentStatus
;;;			pushi    2
;;;			pushi    SOUND
;;;			pushi    2
;;;			callk    Load,  4
;;;			ldi      3
;;;			aTop     seconds
;;;			jmp      code_0107
;;;code_0042:
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_0107
;;;			pushi    2
;;;			pushi    VIEW
;;;			pTos     caller
;;;			callk    Load,  4
;;;			pushi    #stop
;;;			pushi    0
;;;			lag      soundFX
;;;			send     4
;;;			pushi    #number
;;;			pushi    1
;;;			pushi    2
;;;			pushi    #loop
;;;			pushi    1
;;;			pushi    1
;;;			pushi    #priority
;;;			pushi    1
;;;			pushi    255
;;;			pushi    #play
;;;			pushi    0
;;;			lag      music
;;;			send     22
;;;			pushi    #view
;;;			pushi    1
;;;			pTos     caller
;;;			lofsa    deadIcon
;;;			send     6
;;;			pushi    13
;;;			pTos     register
;;;			pushi    #title
;;;			pTos     next
;;;			pushi    #font
;;;			lsg      bigFont
;;;			pushi    #icon
;;;			lofsa    deadIcon
;;;			push    
;;;			pushi    #button
;;;			lofsa    {Keep On Muddling}
;;;			push    
;;;			pushi    0
;;;			pushi    #button
;;;			lofsa    {Order A Hintbook}
;;;			push    
;;;			pushi    1
;;;			calle    Print,  26
;;;			bnt      code_00a8
;;;			pushi    2
;;;			pushi    40
;;;			pushi    0
;;;			calle    Print,  4
;;;code_00a8:
;;;			pushi    15
;;;			pushi    40
;;;			pushi    1
;;;			pushi    #title
;;;			lofsa    {Al says:}
;;;			push    
;;;			pushi    #font
;;;			lsg      bigFont
;;;			pushi    #button
;;;			lofsa    {Restore}
;;;			push    
;;;			pushi    1
;;;			pushi    #button
;;;			lofsa    {Restart}
;;;			push    
;;;			pushi    2
;;;			pushi    #button
;;;			lofsa    {__Quit__}
;;;			push    
;;;			pushi    3
;;;			calle    Print,  30
;;;			push    
;;;			dup     
;;;			ldi      1
;;;			eq?     
;;;			bnt      code_00e3
;;;			pushi    #restore
;;;			pushi    0
;;;			lag      theGame
;;;			send     4
;;;			jmp      code_0103
;;;code_00e3:
;;;			dup     
;;;			ldi      2
;;;			eq?     
;;;			bnt      code_00f5
;;;			pushi    #restart
;;;			pushi    0
;;;			lag      theGame
;;;			send     4
;;;			jmp      code_0103
;;;code_00f5:
;;;			dup     
;;;			ldi      3
;;;			eq?     
;;;			bnt      code_0103
;;;			ldi      1
;;;			sag      quit
;;;			jmp      code_0107
;;;code_0103:
;;;			toss    
;;;			jmp      code_00a8
;;;code_0107:
;;;			toss    
;;;			ret     
;;;		)
;;;	)
;;;)

(instance DyingScript of Script
	(properties)
	;EO: This script is a recreation based on the original asm, which
	; has been commented out but intact above.
	; Also referred to is Brian Provinciano's original SCI0 template,
	; which used this game as the basis.
	
	(method (changeState newState &tmp [buf 133])
		(switch (= state newState)
			(0
				(HandsOff)
				(music fade:)
				(curRoom setScript: 0)
				(= currentStatus egoDEATHMESSAGE)
				(Load SOUND 2)
				(= seconds 3)
			)
			(1
				(Load VIEW caller)
				(soundFX stop:)
				(music number: 2 loop: 1 priority: -1 play:)
				(deadIcon view: caller)
				(if
					(Print register
						#title next
						#font bigFont
						#icon deadIcon
						#button {Keep On Muddling} 0
						#button {Order A Hintbook} 1
					)
					(Print 40 0)
				)
				(repeat
					(switch
						(Print 40 1
							#title {Al says:}
							#font bigFont
							#button {Restore} 1
							#button {Restart} 2
							#button {Quit} 3
						)
						(1
							(theGame restore:)
						)
						(2
							(theGame restart:)
						)
						(3
							(= quit TRUE)
							(break)
						)
					)
				)
			)
		)
	)
)


(instance deadIcon of DCIcon
	(properties)
	
	(method (init)
		(super init:)
		(if (== curRoomNum 540)
			((= cycler (EndLoop new:)) init: self)
		)
	)
)
