;;; Sierra Script 1.0 - (do not remove this comment)
(script# 216)
(include sci.sh)
(use Main)
(use rm201)
(use eureka)
(use Print)
(use Sound)
(use System)

(public
	sCommandFlo 0
	sTalkToFlo 1
)

(local
	local0
	local1
)
(procedure (localproc_008f param1)
	(if (Btst 30)
		(messager say: 28 0 4 (Random 1 3) param1)
	else
		(messager say: 28 0 3 (Random 1 3) param1)
	)
)

(procedure (localproc_00ca param1)
	(if (Btst 30)
		(messager say: 4 0 3 (Random 1 3) param1)
	else
		(messager say: 4 0 4 (Random 1 3) param1)
	)
)

(procedure (localproc_05bd param1)
	(cond 
		((>= (eureka puke?) 4) (messager say: 18 0 16 1 (if argc param1 else 0)))
		((Btst 61) (messager say: 18 0 13 1 (if argc param1 else 0)))
		((< global127 3)
			(if (Btst 31)
				(messager say: 25 0 21 1 (if argc param1 else 0) 202)
			else
				(messager
					say: 18 0 4 (Random 1 3) (if argc param1 else 0)
				)
			)
		)
		((not (Btst 30)) (messager say: 25 0 22 1 (if argc param1 else 0) 202))
		((not (Btst 93)) (messager say: 25 0 26 1 (if argc param1 else 0) 202))
		((== global142 1)
			(if (not (Btst 94))
				(messager say: 25 0 24 0 (if argc param1 else 0) 202)
			else
				(messager say: 18 0 33 1 (if argc param1 else 0))
			)
		)
		((== global142 2)
			(if
			(and (not (Btst 75)) (!= eurekaCurLocation 8))
				(messager say: 25 0 25 0 (if argc param1 else 0) 202)
			else
				(messager say: 18 0 33 1 (if argc param1 else 0))
			)
		)
		((Btst 30)
			(messager
				say: 18 0 3 (Random 1 3) (if argc param1 else 0)
			)
		)
		(else
			(messager
				say: 18 0 4 (Random 1 3) (if argc param1 else 0)
			)
		)
	)
)

(instance sAbandonShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= (eureka puke?) 4)
					(Bset 85)
					(SolvePuzzle 200 10)
					(messager say: 1 0 2 1 self)
				else
					(messager say: 1 0 1 1 self)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sHailPlanet of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: (ScriptID 201 4) self 3)
			)
			(1
				(switch eurekaCurLocation
					(0
						(messager say: 20 0 32 1 self)
					)
					(3
						(messager say: 20 0 31 1 self)
					)
					(32
						(messager say: 20 0 29 1 self)
					)
					(5
						(if (Btst 30)
							(switch global142
								(0
									(messager say: 20 0 27 1 self)
								)
								(else 
									(messager say: 20 0 54 1 self)
								)
							)
						else
							(messager say: 20 0 27 2 self)
						)
					)
					(6
						(cond 
							((Btst 42) (messager say: 20 0 54 1 self))
							((== global142 1) (messager say: 20 0 30 1 self))
							((== global142 2) (messager say: 21 0 33 1 self))
							(else (messager say: 20 0 31 1 self))
						)
					)
					(7
						(messager say: 20 0 28 1 self)
					)
					(8
						(messager say: 20 0 28 2 self)
					)
					(else 
						(cond 
							((OneOf eurekaCurLocation 9 10 11 12 13) (messager say: 20 0 31 1 self))
							((Btst 30) (messager say: 20 0 3 (Random 1 3) self))
							(else (messager say: 20 0 4 (Random 1 3) self))
						)
					)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sHailShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (== eurekaCurLocation 0) (Btst 34))
					(= cycles 1)
				else
					(self setScript: (ScriptID 201 4) self 1)
				)
			)
			(1
				(switch eurekaCurLocation
					(0
						(if (Btst 34)
							(messager say: 21 0 34 0 self)
						else
							(self setScript: (ScriptID 211 0) self)
						)
					)
					(5
						(if
						(and (not (Btst 92)) (Btst 93) (< global142 1))
							(messager say: 21 0 0 1 self)
							(= local1 1)
						else
							(messager say: 21 0 3 3 self)
						)
					)
					(6
						(cond 
							((Btst 42)
								(if (eureka damaged?)
									(messager say: 21 0 20 1 self)
								else
									(messager say: 21 0 22 1 self)
								)
							)
							((Btst 33) (messager say: 21 0 33 1 self))
							((Btst 30) (messager say: 21 0 3 (Random 1 3) self))
							(else (messager say: 21 0 4 (Random 1 3) self))
						)
					)
					(14
						(messager say: 21 0 33 1 self)
					)
					(else 
						(if (Btst 30)
							(messager say: 21 0 3 (Random 1 3) self)
						else
							(messager say: 21 0 4 (Random 1 3) self)
						)
					)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance starConMusic of Sound
	(properties)
)

(instance sHailStarcon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (== eurekaCurLocation 0) (Btst 31))
					(= cycles 1)
				else
					(self setScript: (ScriptID 201 4) self 2)
				)
			)
			(1
				(cond 
					((== eurekaCurLocation 0)
						(if (Btst 31)
							(messager say: 22 0 36 1 self)
						else
							(messager say: 22 0 37 0 self)
							(= local0 1)
							(Bset 31)
						)
					)
					((== eurekaCurLocation 5)
						(narrator keepWindow: 1)
						(theMusic2 setVol: 5)
						(starConMusic loop: -1 number: 223 play:)
						(messager say: 22 0 27 1 self)
					)
					((Btst 33) (messager say: 22 0 33 1 self))
					((Btst 30) (messager say: 22 0 3 (Random 1 3) self))
					(else (messager say: 22 0 4 (Random 1 3) self))
				)
			)
			(2
				(narrator keepWindow: 0)
				(starConMusic dispose:)
				(theMusic2 setVol: 127)
				(self dispose:)
			)
		)
	)
)

(instance sCommandFlo of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 205)
		(DisposeScript 216)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc201_6 self)
			)
			(1
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 2))
				(theGame setCursor: 982 1)
				(switch
					(Print
						mode: 1
						window: (ScriptID 205 0)
						font: userFont
						width: 125
						addColorButton: 0 1 0 0 12 0 0 205 13 29 31 0 0 0
						addColorButton: 1 1 0 0 13 0 10 205 13 29 31 0 0 0
						addColorButton: 2 1 0 0 14 0 20 205 13 29 31 0 0 0
						addColorButton: 3 1 0 0 15 0 30 205 13 29 31 0 0 0
						addColorButton: 4 1 0 0 16 0 40 205 13 29 31 0 0 0
						addColorButton: 5 1 0 0 17 0 50 205 13 29 31 0 0 0
						addColorButton: 6 1 0 0 18 0 60 205 13 29 31 0 0 0
						init:
					)
					(0
						(theGame handsOff:)
						(self setScript: sHailShip self)
					)
					(1
						(theGame handsOff:)
						(self setScript: sHailStarcon self)
					)
					(2
						(theGame handsOff:)
						(self setScript: sHailPlanet self)
					)
					(3
						(theGame handsOff:)
						(localproc_05bd self)
					)
					(4
						(theGame handsOff:)
						(localproc_008f self)
					)
					(5
						(theGame handsOff:)
						(self setScript: sAbandonShip self)
					)
					(6
						(theGame handsOff:)
						(localproc_00ca self)
					)
					(else 
						(theGame handsOff:)
						(= ticks 5)
					)
				)
			)
			(2
				(self setScript: (ScriptID 201 4) self 0)
			)
			(3
				(theGame handsOff:)
				(cond 
					((Btst 85) (= next (ScriptID 201 23)) (self dispose:))
					(local0 (= local0 0) (= next (ScriptID 214 4)) (self dispose:))
					(local1 (= next (ScriptID 211 2)) (self dispose:))
					(else (= cycles 1))
				)
			)
			(4
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 4))
				(theGame setCursor: 984 1)
				(self dispose:)
			)
		)
	)
)

(instance sTalkToFlo of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 220)
		(DisposeScript 216)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1 (proc201_6 self))
			(2
				(cond 
					((== eurekaCurLocation 0) (self setScript: (ScriptID 220 1) self))
					((== global126 1) (self setScript: (ScriptID 220 6) self))
					(
						(and
							(not (Btst 30))
							(< 8 eurekaCurLocation)
							(< eurekaCurLocation 14)
						)
						(self setScript: (ScriptID 220 8) self)
					)
					((Btst 61) (messager say: 12 0 89 0 self 202))
					(
						(and
							(Btst 36)
							(not (Btst 30))
							(or
								(== (eureka prevLocation?) 2)
								(== (eureka curLocation?) 2)
							)
						)
						(self setScript: (ScriptID 220 4) self)
					)
					((and (Btst 30) (not (Btst 93))) (self setScript: (ScriptID 220 5) self))
					(
						(and
							(== eurekaCurLocation 5)
							(Btst 93)
							(not (Btst 76))
						)
						(if (Btst 92)
							(messager say: 12 0 61 0 self 202)
						else
							(messager say: 12 0 62 0 self 202)
						)
					)
					((and (== global142 1) (not (Btst 94))) (self setScript: (ScriptID 220 13) self))
					(
					(and (== eurekaCurLocation 15) (not (Btst 87))) (messager say: 11 2 59 1 self 202))
					((and (== global142 2) (not (Btst 75))) (self setScript: (ScriptID 220 10) self))
					((>= (eureka puke?) 4) (messager say: 12 0 9 3 self 202))
					((and (Btst 75) (!= eurekaCurLocation 14)) (self setScript: (ScriptID 220 12) self))
					((and (Btst 75) (== eurekaCurLocation 14)) (messager say: 12 0 91 0 self 202))
					(else (self setScript: (ScriptID 220 7) self))
				)
			)
			(3
				(self setScript: (ScriptID 201 4) self 0)
			)
			(4
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 3))
				(theGame setCursor: 983 1)
				(self dispose:)
			)
		)
	)
)
