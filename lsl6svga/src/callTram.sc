;;; Sierra Script 1.0 - (do not remove this comment)
(script# 826)
(include sci.sh)
(use Main)
(use fileScr)
(use Print)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	callTram 0
	getOffTram 1
	turnTramLeft 2
	turnTramRight 3
)

(local
	local0
)
(instance callTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 825 1) setMotion: 0)
				(LoadMany 128 88 90)
				(theGame handsOff:)
				(proc79_3 ego (ScriptID 825 1) self)
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
					((and (== register 2) (Btst 270)) (messager say: 1 register 2 0 self 825))
					(else (messager say: 1 register 0 0 self 825))
				)
			)
			(2
				(curRoom
					addObstacle:
						(thePoly
							type: 2
							init:
								((ScriptID 825 1) brLeft?)
								((ScriptID 825 1) brTop?)
								((ScriptID 825 1) brRight?)
								((ScriptID 825 1) brTop?)
								((ScriptID 825 1) brRight?)
								((ScriptID 825 1) brBottom?)
								((ScriptID 825 1) brLeft?)
								((ScriptID 825 1) brBottom?)
							yourself:
						)
				)
				(= cycles 2)
			)
			(3
				(ego
					ignoreActors: 1
					setMotion:
						PolyPath
						((ScriptID 825 1) x?)
						(- ((ScriptID 825 1) y?) 2)
						self
				)
			)
			(4
				((curRoom obstacles?) delete: thePoly)
				(thePoly dispose:)
				(= cycles 2)
			)
			(5
				(= local0 ((ScriptID 825 1) cycleSpeed?))
				(ego state: (& (ego state?) $fffd) dispose:)
				((ScriptID 825 1)
					view: 88
					cycleSpeed: 12
					loop: (if (Btst 36) 0 else 1)
					setCel: 0
					setCycle: End self
				)
			)
			(6
				(theGame handsOn:)
				(theIconBar
					disableIcon:
						(ScriptID 0 3)
						(ScriptID 0 5)
						(ScriptID 0 6)
						(ScriptID 0 9)
					enableIcon: (ScriptID 0 7) (ScriptID 0 4)
					curIcon: (ScriptID 0 7)
					show:
				)
				(theGame
					setCursor: ((theIconBar curIcon?) getCursor:)
					changeScore: 2 270
				)
				((ScriptID 825 1)
					view: 90
					loop: (if (Btst 36) 0 else 1)
					cycleSpeed: local0
					moveSpeed: 1
					xStep: 4
				)
				(Bset 35)
				(= cycles 2)
			)
			(7
				(messager say: 1 0 5 0 self 825)
			)
			(8
				(curRoom setScript: (ScriptID curRoomNum 1))
			)
		)
	)
)

(instance getOffTram of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (changeState newState &tmp temp0 [temp1 2] temp3)
		(switch (= state newState)
			(0
				((ScriptID 825 1) setMotion: 0)
				(LoadMany 128 88 82)
				(theGame handsOff:)
				(if (Btst 70)
					(messager say: 1 0 4 0 self 825)
				else
					(messager say: 1 2 1 0 self 825)
				)
			)
			(1
				((ScriptID 825 1)
					setMotion: 0
					view: 88
					loop: (if (Btst 36) 0 else 1)
					setCel: 3
					moveSpeed: 4
					xStep: 3
					setCycle: Beg self
				)
			)
			(2
				(ego
					x: ((ScriptID 825 1) x?)
					y: (- ((ScriptID 825 1) y?) 2)
					state: (| $0002 (ego state?))
					init:
					normalize: 900
				)
				((ScriptID 825 1) view: 82)
				(Bclr 35)
				(= temp0 -1)
				(= temp3 ((ScriptID 825 0) currentRoom?))
				(if
				(!= ((ScriptID 825 0) currentRoom?) curRoomNum)
					(while (!= temp3 curRoomNum)
						(while (!= ((ScriptID 825 0) at: (++ temp0)) 32767)
						)
						(= temp3 ((ScriptID 825 0) at: (++ temp0)))
					)
					((ScriptID 825 0)
						currentRoom: curRoomNum
						value: (++ temp0)
					)
					(if (!= curRoomNum 820) ((ScriptID 825 0) y: 128))
					(if (Btst 36) ((ScriptID 825 0) next:))
				)
				(if (not (Btst 70))
					((ScriptID 825 1)
						view: 82
						setCycle: Walk
						setMotion: (ScriptID 825 0)
					)
					(theIconBar
						enableIcon:
							(ScriptID 0 3)
							(ScriptID 0 5)
							(ScriptID 0 6)
							(ScriptID 0 9)
						show:
					)
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance turnTramLeft of Script
	(properties)
	
	(method (doit)
		(cond 
			((not (== state 4)))
			((ego inRect: 45 104 225 150))
			(else (self cue:))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 825 1) setMotion: 0)
				((ScriptID 825 2) stop:)
				(= cycles 2)
			)
			(1
				((ScriptID 825 1) setMotion: MoveTo 90 126 self)
			)
			(2
				(theGame handsOff:)
				(Bclr 70)
				((ScriptID 825 1)
					view: 83
					loop: 0
					setCel: 6
					setCycle: Beg self
				)
			)
			(3
				(cond 
					(
						(or
							(theGame script?)
							(and
								(or (talkers size:) (Print dialog?))
								(Print dialog?)
							)
						)
						(-- state)
						(= ticks 90)
					)
					((ego inRect: 45 104 225 150) (messager say: 1 0 3 0 self 825))
					(else (self cue:))
				)
			)
			(4
				(if (not (self script?))
					(self setScript: moveItScript)
				)
				(theGame handsOn:)
				(ego normalize: 900 2)
			)
			(5
				(self setScript: 0)
				(theGame handsOff:)
				(ego setHeading: 180 self)
			)
			(6
				((ScriptID 825 1)
					view: 80
					loop: 0
					setPri: -1
					ignoreActors: 1
				)
				(curt
					init:
					show:
					setPri: 125
					setCycle: Walk
					setMotion: MoveTo 143 125 self
				)
			)
			(7
				(curt setPri: -1 hide:)
				((ScriptID 825 1) loop: 2 posn: 119 133 setCel: 0)
				(= cycles 2)
			)
			(8
				((ScriptID 825 1) cycleSpeed: 8 setCycle: End self)
			)
			(9
				(curt
					show:
					posn: 123 126
					loop: 2
					setCel: 1
					setCycle: Walk
					setMotion: MoveTo 148 126 self
				)
				((ScriptID 825 1)
					view: 80
					loop: 1
					cycleSpeed: 6
					posn: 148 127
				)
			)
			(10
				(curt dispose:)
				((ScriptID 825 1)
					view: 83
					loop: 1
					setCel: 0
					setPri: -1
					setCycle: End self
				)
			)
			(11
				(Bset 36)
				((ScriptID 825 0) next:)
				((ScriptID 825 1)
					view: 90
					setCycle: Walk
					setMotion: (ScriptID 825 0)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance turnTramRight of Script
	(properties)
	
	(method (doit)
		(cond 
			((not (== state 4)))
			((ego inRect: 15 60 175 90))
			(else (self cue:))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 825 1) setMotion: 0)
				((ScriptID 825 2) stop:)
				(= cycles 2)
			)
			(1
				((ScriptID 825 1) setMotion: MoveTo 133 85 self)
			)
			(2
				(Bclr 70)
				(theGame handsOff:)
				((ScriptID 825 1)
					view: 83
					loop: 1
					setCel: 6
					setCycle: Beg self
				)
			)
			(3
				(cond 
					(
						(or
							(theGame script?)
							(and
								(or (talkers size:) (Print dialog?))
								(Print dialog?)
							)
						)
						(-- state)
						(= ticks 90)
					)
					((ego inRect: 15 60 175 90) (messager say: 1 0 3 0 self 825))
					(else (self cue:))
				)
			)
			(4
				(if (not (self script?))
					(self setScript: moveItScript)
				)
				(theGame handsOn:)
				(ego normalize: 900 2)
			)
			(5
				(self setScript: 0)
				(theGame handsOff:)
				(if (not (curRoom script?))
					(ego setHeading: 270 self)
				else
					(self cue:)
				)
			)
			(6
				((ScriptID 825 1) view: 80 loop: 1 ignoreActors: 1)
				(curt
					init:
					show:
					setPri: 80
					posn: 133 84
					setCycle: Walk
					setMotion: MoveTo 82 84 self
				)
			)
			(7
				(curt hide:)
				((ScriptID 825 1) loop: 2 setCel: 4 posn: 104 91)
				(= cycles 2)
			)
			(8
				((ScriptID 825 1) cycleSpeed: 12 setCycle: Beg self)
			)
			(9
				((ScriptID 825 1)
					view: 80
					loop: 0
					cycleSpeed: 6
					posn: 75 85
				)
				(cond 
					((Btst 71)
						(curt dispose:)
						((ScriptID 820 3)
							init:
							approachX: 152
							approachY: 79
							approachVerbs: 42 43
							setLoop: 2
							setCel: 1
							show:
						)
						(theGame handsOn:)
						(self dispose:)
					)
					((and (not (Btst 160)) (Btst 205))
						(Bclr 36)
						((ScriptID 825 0)
							currentRoom: 820
							value: 58
							endType: -1
							next:
						)
						((ScriptID 820 3)
							init:
							approachX: 152
							approachY: 79
							approachVerbs: 42 43
							setLoop: 2
							setCel: 1
						)
						(curt dispose:)
						(ego setScript: (ScriptID 820 2))
						(self dispose:)
					)
					(else
						(curt
							show:
							posn: 128 84
							loop: 2
							setCel: 0
							setCycle: Walk
							setMotion: MoveTo 75 84 self
						)
					)
				)
			)
			(10
				(curt dispose:)
				((ScriptID 825 1)
					view: 83
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(11
				(Bclr 36)
				((ScriptID 825 0)
					currentRoom: 820
					value: 58
					endType: -1
					next:
				)
				((ScriptID 825 1)
					view: 90
					setCycle: Walk
					setMotion: (ScriptID 825 0)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance moveItScript of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0d2d
			ldi      10
			aTop     seconds
			jmp      code_0d78
code_0d2d:
			dup     
			ldi      1
			eq?     
			bnt      code_0d78
			ldi      65535
			aTop     state
			pushi    #script
			pushi    0
			lag      theGame
			send     4
			bt       code_0d58
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_0d58
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_0d58
code_0d58:
			not     
			bnt      code_0d71
			pushi    #say
			pushi    6
			pushi    1
			pushi    0
			pushi    3
			pushi    0
			pushSelf
			pushi    825
			lag      messager
			send     16
			jmp      code_0d78
code_0d71:
			pushi    #cue
			pushi    0
			self     4
code_0d78:
			toss    
			ret     
		)
	)
)

(instance curt of Actor
	(properties
		x 90
		y 126
		view 81
	)
)

(instance thePoly of Polygon
	(properties)
)
