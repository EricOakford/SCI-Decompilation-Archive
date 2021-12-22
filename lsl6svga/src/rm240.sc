;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include sci.sh)
(use Main)
(use fileScr)
(use OccCyc)
(use LarryRoom)
(use rm740)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm240 0
	kenny 1
)

(local
	local0
	local1
	local2
	local3
	local4
	gEgoCycleSpeed
)
(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm240 of LarryRoom
	(properties
		noun 1
		picture 240
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(= local0
			((Polygon new:)
				type: 0
				init: 105 24 120 30 83 48 65 42
				yourself:
			)
		)
		(= local2
			((Polygon new:)
				type: 0
				init: 196 55 217 57 178 82 157 75
				yourself:
			)
		)
		((ScriptID 0 11) init: cueScr)
		(if
		(or (== prevRoomNum 250) (== (ego view?) 239))
			(merrily
				init:
				setPri: 110
				ignoreActors: 1
				setLoop: 6 1
				cycleSpeed: 6
				setCycle: Fwd
				setScript: rubLotion
			)
			(kenny
				init:
				setScript: randomKenny
				ignoreActors: 1
				cycleSpeed: 15
				setCycle: Fwd
			)
			(if (== prevRoomNum 250)
				(ego
					view: 243
					loop: 0
					cel: 0
					x: 97
					y: 117
					actions: egoActions
					init:
					signal: 8225
				)
				(theGame handsOn:)
			else
				(ego
					view: 243
					signal: 8225
					setLoop: 6 1
					setSpeed: 6
					cel: 0
					x: 0
					y: 117
					actions: egoActions
					init:
				)
				(curRoom setScript: rideBeaverIn 0 0)
			)
		else
			(barEdge init: setPri: 30 ignoreActors: 1)
			(waterProp1 init: setCycle: RandOccCycle self 1 10 120)
			(waterProp2 init: setCycle: RandOccCycle self 1 10 120)
			(theGame handsOn:)
		)
		(super init: &rest)
		(cashRegister init:)
		(tipJar init:)
		(poolBar init:)
		(barSign init:)
		(counterTops init:)
		(if (not (Btst 168)) (sunglassCase init:))
	)
	
	(method (dispose)
		(Bclr 40)
		(local0 dispose:)
		(local2 dispose:)
		((ScriptID 0 11) dispose:)
		(if (== (ego view?) 243) (ego view: 239))
		(waitressTimer delete: dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (not (cast contains: merrily))
						(messager say: 1 1 3)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(else 
					(if (not (cast contains: merrily))
						(messager say: 1 0 3)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if (or (talkers size:) (curRoom script?))
			(kennyTimer setReal: curRoom (Random 5 15))
		else
			(curRoom setScript: talkToKenny)
		)
	)
)

(instance randomKenny of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
				(= seconds (Random 5 15))
			)
			(1
				(kenny loop: 4 cycleSpeed: 15 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(kenny setCycle: Fwd loop: 0 setCel: 0)
				(= seconds (Random 10 20))
			)
			(3 (self init:))
		)
	)
)

(instance rubLotion of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 15)))
			(1
				(merrily
					setLoop: 10 1
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(2 (= ticks 30))
			(3
				(merrily setLoop: 11 1 setCycle: Fwd)
				(= seconds 7)
			)
			(4
				(merrily setLoop: 6 1 setCel: 0 setCycle: Fwd)
				(self dispose:)
			)
		)
	)
)

(instance rideBeaverIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(ego
					cycleSpeed: 10
					setPri: 200
					setMotion: MoveTo 97 117 rideCue
				)
				(self cue:)
			)
			(1
				(if register
					(++ state)
					(self cue:)
				else
					(ego cel: 0 setCycle: CT 3 1 self)
				)
			)
			(2
				(sFx number: 233 loop: 1 play:)
				(if (not register) (= state (- state 2)))
				(ego setCycle: End self)
			)
			(3
				(ego loop: 2 setCel: 1 setCycle: Beg self)
			)
			(4
				(ego loop: 0 cel: 0 cycleSpeed: gEgoCycleSpeed setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance slapTail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 4 0 0 self)
			)
			(1
				(ego loop: 2 setSpeed: 12 setCel: 0 setCycle: End self)
			)
			(2
				(ego loop: 3 setCel: 0 setCycle: End self)
			)
			(3
				(sFx number: 233 loop: 1 play: setVol: 127)
				(ego setCel: 0 setCycle: End self)
			)
			(4
				(sFx number: 233 loop: 1 play: setVol: 127)
				(ego setCel: 0 setCycle: End self)
			)
			(5
				(sFx number: 233 loop: 1 play:)
				(= ticks 30)
			)
			(6
				(ego loop: 0 setCycle: 0 setCel: 0)
				(= seconds 5)
			)
			(7
				(if (cast contains: waitress)
					(theGame handsOn:)
					(self dispose:)
				)
				(waitress
					view: 2423
					init:
					loop: 1
					setCel: 0
					setCycle: End self
				)
			)
			(8
				(waitress view: 242 loop: 0 setCycle: Fwd)
				(messager sayRange: 9 0 9 1 3 self)
			)
			(9
				(waitressTimer setReal: waitress 30)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkToKenny of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Btst 40))
					(messager sayRange: 3 2 0 1 3 self)
				else
					(self cue:)
				)
			)
			(1
				(if (Btst 40)
					(messager say: 3 2 0 4 self)
				else
					(self cue:)
				)
			)
			(2
				(= temp0 (Random 1 21))
				(messager say: 3 2 12 temp0 self)
			)
			(3
				(Bset 40)
				(kennyTimer setReal: curRoom (Random 5 15))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance orderDrink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(waitressTimer dispose:)
				(ego
					view: 243
					loop: 7
					setCel: 0
					setSpeed: 12
					setCycle: End self
				)
			)
			(1 (= ticks 180))
			(2
				(theGame changeScore: 10 169)
				(ego setCycle: Beg self)
			)
			(3
				(if local4
					(messager say: 9 51 9 0 self)
				else
					(self cue:)
				)
			)
			(4 (messager say: 9 7 9 0 self))
			(5
				(waitress view: 2422 loop: 0 setCel: 0 setCycle: End self)
			)
			(6
				(waitress loop: 1 setCel: 0 setCycle: Fwd)
				(= seconds 5)
			)
			(7
				(waitress
					view: 2423
					setCycle: 0
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(8 (= ticks 310))
			(9
				(waitress view: 2423 loop: 2 setCel: 0 setCycle: End self)
			)
			(10
				(waitress
					view: 2421
					loop: 3
					setCel: 0
					setPri: 101
					setCycle: CT 5 1 self
				)
			)
			(11
				(glass2 setPri: 100 ignoreActors: 1 init:)
				(waitress setCycle: End self)
			)
			(12
				(waitress loop: 2 setCel: 0 setCycle: CT 4 1 self)
			)
			(13
				(glass1 setPri: 100 ignoreActors: 1 init:)
				(waitress setCycle: End self)
			)
			(14
				(waitress
					view: 242
					loop: 0
					setPri: (waitress y?)
					setCycle: Fwd
				)
				(= cycles 2)
			)
			(15
				(messager sayRange: 9 0 10 1 3 self)
			)
			(16
				(glass1 hide:)
				(ego view: 243 loop: 4 setCel: 0 setCycle: End self)
				(merrily setScript: merrilyDrinks)
			)
			(17
				(ego loop: 5 setCel: 0 setCycle: CT 2 1 self)
			)
			(18
				(messager sayRange: 9 0 10 4 5)
				(= ticks 90)
			)
			(19
				(ego cycleSpeed: 8 setCycle: End self)
			)
			(20
				(ego loop: 4 setCel: 4 setCycle: Beg self)
			)
			(21
				(glass1 show:)
				(ego view: 243 loop: 0 setCel: 0)
				(= cycles 2)
			)
			(22
				(if (talkers size:) (-- state))
				(= ticks 90)
			)
			(23
				(waitress view: 2423 loop: 0 setCel: 0 setCycle: End self)
			)
			(24
				(waitress dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance merrilyDrinks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 12 1 setCel: 0 setCycle: CT 1 1 self)
			)
			(1
				(client cel: 2)
				(glass2 hide:)
				(= cycles 2)
			)
			(2 (client setCycle: End self))
			(3
				(client setLoop: 13 1 setCel: 0 setCycle: End self)
			)
			(4 (= seconds 10))
			(5
				(client setLoop: 12 1 setCel: 6 setCycle: CT 2 -1 self)
			)
			(6
				(client cel: 1)
				(glass2 show:)
				(= cycles 2)
			)
			(7 (client setCycle: Beg self))
			(8
				(client setLoop: 6 1 setCycle: Fwd)
				(self dispose:)
			)
		)
	)
)

(instance waitressLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 9 0 9 4 self)
			)
			(1
				(waitress view: 2423 loop: 0 setCel: 0 setCycle: End self)
			)
			(2
				(waitress dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getGlasses of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager sayRange: 8 5 0 1 2 self)
			)
			(2
				(theGame changeScore: 7 168)
				(sunglassCase dispose:)
				(ego get: 30)
				(messager say: 8 5 0 3 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toCU of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Btst 102))
					(messager say: 2 2 11 0 self)
				else
					(self cue:)
				)
			)
			(1 (curRoom newRoom: 250))
		)
	)
)

(instance cueScr of Script
	(properties)
	
	(method (cue)
		(curRoom newRoom: 230)
	)
)

(instance barSign of Feature
	(properties
		noun 12
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 113 13 108 0 142 0 142 9
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance counterTops of Feature
	(properties
		noun 13
		y 69
	)
	
	(method (onMe theObjOrX)
		(if (local0 onMe: (theObjOrX x?) (theObjOrX y?))
		else
			(local2 onMe: (theObjOrX x?) (theObjOrX y?))
		)
	)
)

(instance poolBar of Feature
	(properties
		noun 4
		y 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						53
						65
						64
						47
						64
						41
						105
						24
						111
						26
						159
						21
						160
						27
						204
						35
						217
						33
						228
						38
						228
						64
						208
						86
						156
						111
						118
						111
						91
						104
						54
						78
						53
						66
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(switch theVerb
				(4
					(if (== (ego view?) 243)
						(messager say: 4 4 1)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(1
					(if (== (ego view?) 243)
						(messager say: 4 1 1)
					else
						(messager say: 4 1 3)
					)
					(return 1)
				)
				(2
					(if (== (ego view?) 243)
						(messager say: 4 2 1)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance cashRegister of Feature
	(properties
		noun 11
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 162 24 169 5 186 6 185 24 173 28 162 25
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance tipJar of Feature
	(properties
		noun 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 204 12 214 12 214 30 204 30
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance egoTail of Prop
	(properties
		noun 5
		x 97
		y 117
		view 243
		loop 1
		cel 2
	)
	
	(method (doit &tmp propCel)
		(cond 
			((== (= propCel (self cel?)) 2) (ego posn: 97 119) (egoTail posn: 97 118))
			((== propCel 3) (ego posn: 97 118) (egoTail posn: 97 117))
			((== propCel 0) (ego posn: 97 117) (egoTail posn: 97 116))
			((== propCel 1) (ego posn: 97 118) (egoTail posn: 97 117))
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (Btst 169)
						(messager say: 5 4 11)
					else
						(curRoom setScript: slapTail)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance waitress of Prop
	(properties
		noun 9
		x 139
		y 71
		view 242
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb 52 51)
				(= local4 1)
				(curRoom setScript: orderDrink)
			)
			(7
				(curRoom setScript: orderDrink)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if (curRoom script?)
			(waitressTimer setReal: waitress 5)
		else
			(self setScript: waitressLeaves)
		)
	)
)

(instance merrily of Prop
	(properties
		noun 2
		x 198
		y 106
		view 241
		loop 8
		cel 2
	)
	
	(method (doit &tmp propCel)
		(= propCel (self cel?))
		(if (== (self loop?) 6)
			(cond 
				((== propCel 2) (merrily posn: 198 108))
				((== propCel 3) (merrily posn: 198 107))
				((== propCel 0) (merrily posn: 198 106))
				((== propCel 1) (merrily posn: 198 107))
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(if (and (Btst 169) (OneOf theVerb 1 4 2))
			(curRoom setScript: toCU)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance kenny of Prop
	(properties
		noun 3
		x 20
		y 49
		view 241
	)
	
	(method (doit &tmp propCel)
		(cond 
			((== (= propCel (self cel?)) 1) (kenny posn: 20 50))
			((== propCel 2) (kenny posn: 20 51))
			((== propCel 3) (kenny posn: 20 50))
			((== propCel 0) (kenny posn: 20 49))
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (not local3)
						(messager say: 3 4 4)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(1
					(if (not local3)
						(messager say: 3 1 4)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(2
					(= local3 1)
					(curRoom setScript: talkToKenny)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance barEdge of View
	(properties
		y 139
		view 244
		signal $5021
	)
)

(instance sunglassCase of View
	(properties
		noun 8
		x 88
		y 70
		priority 200
		fixPriority 1
		view 240
		loop 1
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(5
					(curRoom setScript: getGlasses)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance glass1 of View
	(properties
		x 114
		y 81
		view 240
	)
)

(instance glass2 of View
	(properties
		x 179
		y 66
		view 240
	)
)

(instance waterProp1 of Prop
	(properties
		x 137
		y 67
		view 245
		cel 4
		cycleSpeed 25
	)
)

(instance waterProp2 of Prop
	(properties
		x 149
		y 111
		view 245
		loop 1
		cel 4
		cycleSpeed 25
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (Btst 169)
						(messager say: 5 4 11)
					else
						(curRoom setScript: slapTail)
					)
					(return 1)
				)
				(1
					(messager say: 5 1)
					(return 1)
				)
				(2
					(messager say: 5 2)
					(return 1)
				)
				(else 
					(super doVerb: theVerb)
					(return 1)
				)
			)
		)
	)
)

(instance rideCue of cObj
	(properties)
	
	(method (cue)
		(rideBeaverIn register: 1)
	)
)

(instance waitressTimer of Timer
	(properties)
)

(instance kennyTimer of Timer
	(properties)
)

(instance sFx of Sound
	(properties)
)
