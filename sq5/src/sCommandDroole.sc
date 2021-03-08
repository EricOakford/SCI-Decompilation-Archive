;;; Sierra Script 1.0 - (do not remove this comment)
(script# 217)
(include sci.sh)
(use Main)
(use rm201)
(use eureka)
(use Print)
(use Sound)
(use System)

(public
	sCommandDroole 0
	sTalkToDroole 1
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
)
(procedure (localproc_0222 param1)
	(= local6 0)
	(cond 
		((Btst 38)
			(Bclr 38)
			(eureka
				destination: 0
				state: 4
				setScript: 0
				warnings: 0
				timer: 0
			)
			(switch (eureka curLocation?)
				(1 (Bset 35))
				(2 (Bset 36))
			)
			((ScriptID 201 12) talkWidth: 60)
			(messager say: 42 0 53 1 param1)
			(= local6 1)
		)
		((== (eureka puke?) 2)
			((ScriptID 201 12) talkWidth: 60)
			(messager say: 42 0 53 1 param1)
			(= local6 1)
		)
		((Btst 30) (messager say: 2 0 3 (Random 1 3) param1))
		(else (messager say: 2 0 4 (Random 1 3) param1))
	)
	((ScriptID 201 12) talkWidth: 120)
)

(procedure (localproc_04b8 param1)
	(cond 
		((== eurekaCurLocation 0)
			(if (not (eureka destination?))
				(messager say: 24 0 46 1 param1)
			else
				(messager say: 31 0 23 1 param1)
				(= local2 1)
			)
		)
		((Btst 32)
			(= local4 1)
			(Bclr 32)
			((ScriptID 201 12) talkWidth: 60)
			(messager say: 42 0 53 1 param1)
			(eureka warnings: 0 setScript: 0 timer: 0)
			(if (== (eureka state?) 2)
				(eureka curLocation: (eureka destination?))
			else
				(eureka setScript: (ScriptID 210 2) 0 30)
			)
		)
		((Btst 30) (messager say: 31 0 3 (Random 1 3) param1))
		(else (messager say: 31 0 4 (Random 1 3) param1))
	)
	((ScriptID 201 12) talkWidth: 120)
)

(procedure (localproc_05c2 param1)
	(cond 
		((== (eureka destination?) 0)
			(if (Btst 30)
				(messager say: 24 0 3 (Random 1 3) param1)
			else
				(messager say: 24 0 46 1 param1)
			)
		)
		((== eurekaCurLocation 0) (messager say: 24 0 41 1 param1) (= local2 1))
		((Btst 32) (messager say: 24 0 45 1 param1))
		((Btst 39) (messager say: 24 0 19 1 param1))
		(else
			(Bset 32)
			(= local5 1)
			(eureka setScript: 0 timer: 0 warnings: 0)
			((ScriptID 201 12) talkWidth: 60)
			(messager say: 42 0 53 1 param1)
			(if (eureka curLocation?)
				(if
					(or
						(== (eureka curLocation?) 15)
						(== (eureka curLocation?) 6)
					)
					(DisposeScript 221)
				)
				(eureka prevLocation: (eureka curLocation?))
				(eureka curLocation: 0)
			)
		)
	)
	((ScriptID 201 12) talkWidth: 120)
)

(procedure (localproc_06f6 param1)
	(cond 
		((or (Btst 32) (!= (eureka state?) 2))
			(if (Btst 30)
				(messager say: 35 0 3 (Random 1 3) param1)
			else
				(messager say: 35 0 4 (Random 1 3) param1)
			)
		)
		(
			(and
				(OneOf (eureka curLocation?) 1 2)
				(== (eureka state?) 2)
			)
			(messager say: 35 0 48 1 param1)
		)
		((Btst 37) (messager say: 35 0 4 3 param1))
		(
		(and (== (eureka state?) 2) (eureka curLocation?))
			(messager say: 35 0 0 1 param1)
			(eureka
				destination: 0
				state: 3
				setScript: 0
				warnings: 0
				timer: 0
			)
			(= local7 1)
		)
		(else (messager say: 35 0 3 (Random 1 3) param1))
	)
)

(procedure (localproc_0800 param1)
	(cond 
		((eureka damaged?) (messager say: 12 0 11 1 param1))
		((== eurekaCurLocation 14)
			(switch (eureka puke?)
				(1
					(messager say: 12 0 15 1 param1)
				)
				(2
					(messager say: 12 0 18 1 param1)
					(param1 cue:)
				)
				(4
					(messager say: 12 0 16 1 param1)
				)
				(else 
					(messager say: 12 0 3 (Random 1 3) param1)
				)
			)
		)
		((Btst 30) (messager say: 12 0 3 (Random 1 3) param1))
		(else (messager say: 12 0 4 (Random 1 3) param1))
	)
)

(procedure (localproc_09b3 param1)
	(= local3 0)
	(cond 
		((Btst 40)
			(if (Btst 30)
				(messager say: 29 0 3 (Random 1 3) param1)
			else
				(messager say: 29 0 4 (Random 1 3) param1)
			)
		)
		((eureka hits?) (messager say: 29 0 11 1 param1))
		(else (messager say: 29 0 0 1 param1) (Bset 40) (= local3 1))
	)
)

(procedure (localproc_0a93 param1)
	(= local3 0)
	(cond 
		((Btst 40) (messager say: 25 0 47 1 param1) (Bclr 40) (= local3 1))
		((Btst 30) (messager say: 25 0 3 (Random 1 3) param1))
		(else (messager say: 25 0 4 (Random 1 3) param1))
	)
)

(procedure (localproc_0af8 param1)
	(if (Btst 30)
		(messager say: 27 0 3 (Random 1 3) param1)
	else
		(messager say: 27 0 4 (Random 1 3) param1)
	)
)

(instance sLayInCourse of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local0 0) (= cycles 1))
			(1
				(cond 
					((eureka damaged?)
						(if (eureka hits?)
							(if (Btst 30)
								(messager say: 23 0 38 (Random 1 3) self)
							else
								(messager say: 23 0 39 (Random 1 3) self)
							)
						else
							(messager say: 23 0 44 1 self)
						)
					)
					(
						(and
							(== eurekaCurLocation 6)
							(Btst 45)
							(== global142 1)
						)
						(messager say: 23 0 22 1 self)
						(= local0 0)
					)
					(
					(and (== eurekaCurLocation 15) (not (Btst 87))) (= local0 0) (messager say: 23 0 40 1 self))
					((== (eureka state?) 0)
						(if (Btst 31)
							(messager say: 23 0 36 1 self)
							(= local0 1)
						else
							(messager say: 23 0 37 1 self)
						)
					)
					((eureka destination?) (messager say: 23 0 41 1 self) (= local0 1))
					(
						(and
							(== (eureka curLocation?) 5)
							(not (Btst 76))
							(Btst 30)
						)
						(messager say: 23 0 27 1 self)
						(= local0 1)
					)
					((or (Btst 37) (== (eureka curLocation?) 14)) (messager say: 23 0 15 1 self))
					(else
						(if (and (not (Btst 30)) (== global127 3))
							(messager say: 19 0 0 (Random 1 3) self 202)
						else
							(messager say: 23 0 36 1 self)
						)
						(= local0 1)
					)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch eurekaCurLocation
					(0
						(messager say: 15 0 23 1 self)
					)
					(6
						(if (Btst 45)
							(if (not (eureka hits?))
								(messager say: 15 0 22 1 self)
							else
								(messager say: 15 0 11 1 self)
							)
						else
							(messager say: 15 0 3 (Random 1 3) self)
						)
					)
					(14
						(switch (eureka puke?)
							(0
								(if (Btst 39)
									(messager say: 15 0 19 1 self)
									(= cycles 1)
								else
									(messager say: 15 0 24 1 self)
									(= cycles 1)
								)
							)
							(1
								(eureka setScript: 0 timer: 0 warnings: 0)
								((ScriptID 201 12) talkWidth: 60)
								(messager say: 42 0 53 1 self)
								(Bclr 39)
								(= local1 1)
							)
							(else 
								(messager say: 15 0 18 1 self)
							)
						)
					)
					(else 
						(cond 
							((eureka damaged?) (messager say: 15 0 11 1 self))
							((Btst 30) (messager say: 15 0 3 (Random 1 3) self))
							(else (messager say: 15 0 4 (Random 1 3) self))
						)
					)
				)
			)
			(1
				((ScriptID 201 12) talkWidth: 120)
				(self dispose:)
			)
		)
	)
)

(instance sEvasiveAction of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch eurekaCurLocation
					(3
						(messager say: 13 0 4 2 self)
					)
					(6
						(if (eureka hits?)
							(self setScript: (ScriptID 220 0) self)
						else
							(messager say: 13 0 3 3 self)
						)
					)
					(14
						(if (Btst 39)
							(messager say: 13 0 19 1 self)
						else
							(messager say: 13 0 21 1 self)
						)
					)
					(else 
						(if (Btst 30)
							(messager say: 13 0 3 (Random 1 3) self)
						else
							(messager say: 13 0 4 (Random 1 3) self)
						)
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance theMusic3 of Sound
	(properties)
)

(instance sShields of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(theMusic3 number: 106 loop: 1 play: self)
			)
			(2
				(theMusic3 number: 233 loop: 1 play: self)
			)
			(3
				(theMusic3 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance navSound of Sound
	(properties
		number 211
	)
)

(instance sCoordsLocked of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(or (not (eureka destination?)) (== (proc201_22) -1))
					(self dispose:)
				else
					(navSound setLoop: 1 play:)
					(= seconds 3)
				)
			)
			(1 (proc201_7 self))
			(2
				(if (Btst 32)
					(messager say: 11 0 41 2 self)
				else
					(messager say: 11 0 41 1 self)
				)
			)
			(3
				(self setScript: (ScriptID 201 8) self)
				(navSound dispose:)
			)
			(4
				(switch (eureka state?)
					(3
						(self setScript: (ScriptID 201 37) self)
					)
					(1
						(if
						(== (eureka destination?) (eureka curLocation?))
							(self setScript: (ScriptID 201 38) self)
						else
							(= cycles 1)
						)
					)
					(2
						(if
						(== (eureka destination?) (eureka curLocation?))
							(self setScript: (ScriptID 201 38) self)
						else
							(= cycles 1)
						)
					)
					(else  (= cycles 1))
				)
			)
			(5 (self dispose:))
		)
	)
)

(instance sCommandDroole of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 205)
		(DisposeScript 217)
	)
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc201_7 self)
				(ego setLoop: 0 setCel: 0 posn: 134 95 show: stopUpd:)
			)
			(1
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 2))
				(theGame setCursor: 982 1)
				(Print
					mode: 1
					font: userFont
					window: (ScriptID 205 0)
					width: 125
					addColorButton: 0 1 0 0 1 0 0 205 13 29 31 0 0 0
					addColorButton: 1 1 0 0 2 0 10 205 13 29 31 0 0 0
					addColorButton: 2 1 0 0 3 0 20 205 13 29 31 0 0 0
					addColorButton: 3 1 0 0 4 0 30 205 13 29 31 0 0 0
					addColorButton: 4 1 0 0 5 0 40 205 13 29 31 0 0 0
					addColorButton: 5 1 0 0 6 0 50 205 13 29 31 0 0 0
				)
				(= register
					(Print
						addColorButton: 6 1 0 0 7 0 60 205 13 29 31 0 0 0
						addColorButton: 7 1 0 0 8 0 70 205 13 29 31 0 0 0
						addColorButton: 8 1 0 0 9 0 80 205 13 29 31 0 0 0
						addColorButton: 9 1 0 0 10 0 90 205 13 29 31 0 0 0
						addColorButton: 10 1 0 0 11 0 100 205 13 29 31 0 0 0
						init:
					)
				)
				(= cycles 1)
			)
			(2
				(theGame handsOff:)
				(switch register
					(0
						(self setScript: sLayInCourse self)
					)
					(1 (localproc_0222 self))
					(2 (self setScript: sFire self))
					(3 (localproc_04b8 self))
					(4 (localproc_05c2 self))
					(5 (localproc_06f6 self))
					(6 (localproc_0800 self))
					(7
						(self setScript: sEvasiveAction self)
					)
					(8 (localproc_09b3 self))
					(9 (localproc_0a93 self))
					(10 (localproc_0af8 self))
					(else  (localproc_0af8 self))
				)
			)
			(3
				(if local0
					(proc201_33)
					(self setScript: (ScriptID 203 0) self 0)
				else
					(= cycles 1)
				)
			)
			(4
				(ego hide:)
				((ScriptID 205 0) dispose:)
				(self setScript: (ScriptID 201 8) self)
			)
			(5 (= seconds 1))
			(6
				(cond 
					(local0 (= local0 0) (self setScript: sCoordsLocked self))
					(local6
						(if (== (eureka puke?) 2)
							(SolvePuzzle 199 200)
							(= next (ScriptID 207 3))
							(self dispose:)
						else
							(= next (ScriptID 232 3))
							(self dispose:)
						)
					)
					(local7 (= local7 0) (= next (ScriptID 201 36)) (self dispose:))
					(local1
						(= local1 0)
						(= next (ScriptID 207 1))
						(SolvePuzzle 198 50)
						(self dispose:)
					)
					(local2 (= local2 0) (= next (ScriptID 214 5)) (self dispose:))
					(local4 (= local4 0) (= next (ScriptID 201 1)) (self dispose:))
					(local5 (= local5 0) (= next (ScriptID 201 35)) (self dispose:))
					(local3 (self setScript: sShields self) (= local3 0))
					(else (= cycles 1))
				)
			)
			(7 (= seconds 1))
			(8
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 4))
				(theGame setCursor: 984 1)
				(self dispose:)
			)
		)
	)
)

(instance sTalkToDroole of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 220)
		(DisposeScript 217)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(proc201_7 self)
				(ego setLoop: 0 posn: 134 95 setCel: 0 show:)
			)
			(2
				(cond 
					((== eurekaCurLocation 0) (self setScript: (ScriptID 220 2) self))
					((and (not (Btst 111)) (== global126 1)) (messager say: 26 0 31 0 self 202) (Bset 111))
					(
						(and
							(Btst 36)
							(not (Btst 61))
							(not (Btst 30))
							(or
								(== (eureka prevLocation?) 2)
								(== (eureka curLocation?) 2)
							)
							(!= (eureka curLocation?) 1)
							(!= (eureka curLocation?) 3)
						)
						(self setScript: (ScriptID 220 3) self)
					)
					((and (not (Btst 30)) (not (Btst 61))) (self setScript: (ScriptID 220 16) self))
					((and (Btst 30) (not (Btst 93))) (self setScript: (ScriptID 220 9) self))
					(
						(and
							(== eurekaCurLocation 5)
							(Btst 93)
							(not (Btst 76))
							(not (Btst 112))
						)
						(messager say: 26 0 63 0 self 202)
						(Bset 112)
					)
					((and (== global142 1) (not (Btst 94))) (self setScript: (ScriptID 220 15) self))
					(
					(and (== eurekaCurLocation 15) (not (Btst 87))) (messager say: 10 2 59 1 self 202))
					((and (== global142 2) (not (Btst 75))) (self setScript: (ScriptID 220 11) self))
					((>= (eureka puke?) 4) (messager say: 13 0 6 2 self 202))
					((and (Btst 75) (!= eurekaCurLocation 14)) (messager say: 26 0 78 0 self 202))
					((and (Btst 75) (== eurekaCurLocation 14)) (messager say: 26 0 14 0 self 202))
					(else (messager say: 10 2 7 1 self 202))
				)
			)
			(3
				(ego hide:)
				(self setScript: (ScriptID 201 8) self)
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
