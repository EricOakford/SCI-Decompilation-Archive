;;; Sierra Script 1.0 - (do not remove this comment)
(script# 218)
(include sci.sh)
(use Main)
(use eureka)
(use Print)
(use Sound)
(use System)

(public
	sCommandCliffy 0
)

(instance theMusic3 of Sound
	(properties)
)

(instance sCommandCliffy of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 218)
		(DisposeScript 205)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 202 13) setScript: 0)
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 2))
				(theGame setCursor: 982 1)
				(= register
					(Print
						mode: 1
						window: (ScriptID 205 0)
						width: 125
						addColorButton: 0 1 0 0 19 0 0 205 13 29 31 0 0 0
						addColorButton: 1 1 0 0 20 0 10 205 13 29 31 0 0 0
						addColorButton: 2 1 0 0 21 0 20 205 13 29 31 0 0 0
						addColorButton: 3 1 0 0 22 0 30 205 13 29 31 0 0 0
						addColorButton: 4 1 0 0 23 0 40 205 13 29 31 0 0 0
						init:
					)
				)
				(= cycles 1)
			)
			(2
				(switch register
					(0
						(self setScript: sStatusReport self)
					)
					(1
						(self setScript: sCloakShip self)
					)
					(2
						(self setScript: sDeCloakShip self)
					)
					(3
						(self setScript: sMorePower self)
					)
					(4
						(self setScript: sForgetIt self)
					)
					(else  (= cycles 1))
				)
			)
			(3
				(if
				(and (== eurekaCurLocation 14) (not (Btst 39)))
					(eureka setScript: (ScriptID 210 1) 0 1)
				)
				(if (!= register 4)
					(messager say: 43 0 0 1 self)
					(= state 0)
				else
					(= cycles 1)
				)
			)
			(4
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 2))
				(theGame setCursor: 982 1)
				(self dispose:)
			)
		)
	)
)

(instance sStatusReport of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((Btst 61) (messager say: 8 0 13 1 self))
					((and (> global126 1) (not (Btst 56))) (messager say: 8 0 12 1 self))
					((and (== eurekaCurLocation 14) (Btst 39)) (messager say: 8 0 55 1 self))
					((eureka damaged?) (messager say: 8 0 11 1 self))
					((>= (eureka puke?) 4) (messager say: 8 0 16 1 self))
					((and (== wd40State 2) (not (Btst 47))) (messager say: 8 0 5 1 self) (Bset 47))
					((Btst 30) (messager say: 8 0 3 (Random 1 3) self))
					(else (messager say: 8 0 4 (Random 1 3) self))
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sCloakShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((Btst 39) (messager say: 7 0 9 1 self))
					((not (Btst 89))
						(if (Btst 30)
							(messager say: 7 0 3 (Random 1 3) self)
						else
							(messager say: 7 0 4 (Random 1 3) self)
						)
					)
					((Btst 32) (messager say: 7 0 45 1 self))
					((and (== global142 2) (Btst 75)) (Bset 39) (self setScript: sCloaking self))
					(else (messager say: 7 0 10 1 self))
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sCloaking of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 42 0 52 1 self)
			)
			(1 (= seconds 2))
			(2
				(if (Btst 37)
					(SolvePuzzle 194 50)
					(eureka setScript: (ScriptID 210 4) 0 1)
				)
				(theMusic3 number: 106 loop: 1 play: self)
			)
			(3
				(theMusic3 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sDeCloakShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 39))
					(if (Btst 30)
						(messager say: 10 0 3 (Random 1 3) self)
					else
						(messager say: 10 0 4 (Random 1 3) self)
					)
				else
					(self setScript: sCloaking self)
					(Bclr 39)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sMorePower of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 26 0 0 0 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance sForgetIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 30)
					(messager say: 17 0 3 (Random 1 3) self)
				else
					(messager say: 17 0 4 (Random 1 3) self)
				)
			)
			(1 (self dispose:))
		)
	)
)
