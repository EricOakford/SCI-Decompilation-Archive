;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include sci.sh)
(use Main)
(use String)
(use Print)
(use System)

(public
	aboutScr 0
)

(instance aboutScr of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if
			(and
				(== ((= temp0 ((user curEvent?) new:)) type?) 4)
				(== (temp0 message?) 27)
			)
			(= register 20)
		)
		(if (& (temp0 modifiers?) $000b) (= register 20))
		(temp0 dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= gGUserControls (user controls?))
				(= gGUserInput (user input?))
				(= gGButtonBarGetCursor (theIconBar getCursor:))
				(theGame handsOff:)
				(SetCursor 98 0 0)
				(= gNewStr_3 (Str new:))
				(Message 0 93 8 0 2 1 (gNewStr_3 data?))
				(= register 0)
				(= cycles 2)
			)
			(1
				(cond 
					(
						(and
							(or (talkers size:) (Print dialog?))
							(Print dialog?)
						)
						(-- state)
						(= ticks 90)
					)
					((<= (++ register) 20)
						(= gNewStr_4 (Str new:))
						(Message 0 93 8 0 0 register (gNewStr_4 data?))
						(cond 
							((== register 1) (= temp0 0))
							((and (> register 15) (< register 19)) (= temp0 1925))
							((== register 20)
								(gNewStr_4 format: (gNewStr_4 data?) version)
								(= temp0 (+ 1907 register))
							)
							((> register 18) (= temp0 (+ 1907 register)))
							(else (= temp0 (+ 1910 register)))
						)
						(if (not temp0)
							(Print
								font: userFont
								addTitle: (gNewStr_3 data?)
								addText: (gNewStr_4 data?) 2 1
							)
						else
							(Print
								font: userFont
								addTitle: (gNewStr_3 data?)
								addIcon: temp0 0 0 0 0
								mode: 1
								addText: (gNewStr_4 data?) 60 1
							)
						)
						((Print dialog?) setSize:)
						(Print init: self)
						(gNewStr_4 dispose:)
						(= gNewStr_4 0)
					)
					(else (self cue:))
				)
			)
			(2
				(if (< register 20) (= state (- state 2)))
				(= cycles 4)
			)
			(3
				(gNewStr_3 dispose:)
				(= gNewStr_3 0)
				(theGame handsOn:)
				(if (not gGUserControls)
					(theIconBar disableIcon: (ScriptID 0 3) show:)
				)
				(theGame setCursor: gGButtonBarGetCursor)
				(user canInput: gGUserInput canControl: gGUserControls)
				(self dispose:)
			)
		)
	)
)
