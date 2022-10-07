;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include sci.sh)
(use Main)
(use KQ6Print)
(use KQ6Room)
(use Kq6Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Smooper)
(use Motion)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	local0
	local1
	[local2 16] = [37 122 85 157 246 157 286 141 276 139 241 151 89 151 45 121]
	[local18 16] = [33 137 75 159 207 149 255 122 233 122 203 141 84 152 48 134]
)
(instance rm390 of KQ6Room
	(properties
		noun 3
		picture 390
		horizon 0
	)
	
	(method (init)
		(= local0 10)
		(curRoom
			addObstacle: (roomPoly points: @local2 yourself:)
		)
		(Palette palSET_INTENSITY 64 223 60)
		(super init: &rest)
		(LoadMany 128 3931 390 3903 3904 391 392 393)
		(features
			add: opening floor westEnd eastEnd mintHole
			eachElementDo: #init
		)
		(theGlobalSound number: 390 setLoop: -1 play:)
		(curRoom setScript: enterScr)
	)
	
	(method (doit)
		(Wait 3)
		(super doit: &rest)
	)
	
	(method (dispose)
		(Palette palSET_INTENSITY 64 223 100)
		(theGlobalSound fade: 0 10 10)
		(DisposeScript 968)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 3 1 local0 1)
				1
			)
			(5
				(if (== local0 10)
					(messager say: 3 5 local0 1)
					1
				else
					(super doVerb: theVerb &rest)
				)
			)
			(2
				(if (== local0 10)
					(self setScript: caveTalkScr)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(20
				(if (== local0 10)
					(curRoom setScript: lightItUp)
				else
					1
					(messager say: 2 20 local0 1)
				)
			)
			(else 
				(if (== local0 10)
					(messager say: 3 0 local0 1)
					1
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
		(return 1)
	)
)

(instance roomPoly of Polygon
	(properties
		size 8
		type $0003
	)
)

(instance mySmooper of SmoothLooper
	(properties
		cycleSpeed 6
		vChangeDir 3931
	)
)

(instance floor of Feature
	(properties
		x 160
		y 150
		noun 13
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== local0 10)
					(messager say: 12 1 10 1)
				else
					(messager say: 13 1 11 1)
				)
			)
			(5
				(if (== local0 10)
					(messager say: 12 5 10 1)
				else
					(messager say: 13 5 11 1)
				)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance toCave1 of Feature
	(properties
		x 30
		y 130
		noun 11
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 11 1 local0 1)
			)
			(5
				(curRoom setScript: crawl2Cave1)
			)
			(2
				(rm390 doVerb: theVerb &rest)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance opening of Feature
	(properties
		x 40
		y 115
		noun 11
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 11 1 local0 1)
			)
			(5
				(curRoom setScript: leaveHere)
			)
			(2
				(rm390 doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mintHole of Feature
	(properties
		x 290
		y 130
		noun 12
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 12 1 local0 1)
			)
			(2
				(rm390 doVerb: theVerb &rest)
			)
			(5
				(if (== local0 11)
					(curRoom setScript: crawl2Cave2)
				else
					(messager say: 12 5 10 1)
				)
			)
			(else 
				(if (== local0 11)
					(messager say: 12 0 11 1)
				else
					(messager say: 3 0 10 1)
				)
			)
		)
	)
)

(instance westEnd of Feature
	(properties
		x 10
		y 150
		onMeCheck $0020
	)
	
	(method (init)
		(walkHandler add: self)
		(super init:)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 3)
			(messager say: 1 0 1 1)
		else
			(rm390 doVerb: theVerb &rest)
		)
	)
)

(instance eastEnd of Feature
	(properties
		x 310
		y 150
		onMeCheck $0040
	)
	
	(method (init)
		(walkHandler add: self)
		(super init:)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 3)
			(messager say: 1 0 4 1)
		else
			(rm390 doVerb: theVerb &rest)
		)
	)
)

(instance pepperBush of View
	(properties
		x 277
		y 94
		noun 7
		approachX 238
		approachY 127
		view 390
		loop 1
		cel 2
		priority 2
		signal $5010
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: 1 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (not (ego has: 31))
					(curRoom setScript: getLeaf)
				else
					(messager say: 7 5 8 1)
				)
			)
			(1 (messager say: 7 1 0 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ledge of View
	(properties
		x 249
		y 103
		noun 14
		view 390
		loop 1
		priority 2
		signal $5010
	)
)

(instance rBlock of View
	(properties
		x 265
		y 103
		noun 3
		onMeCheck $0000
		view 390
		priority 1
		signal $4010
	)
)

(instance myHeadingCode of Code
	(properties)
	
	(method (doit param1 param2)
		(if argc
			(if (> param1 180) (= param1 270) else (= param1 90))
		)
		(if (ego looper?)
			((ego looper?)
				doit: ego param1 (if (>= argc 2) param2 else 0)
			)
		else
			(ego heading: param1)
			(DirLoop ego param1)
			(if (and (>= argc 2) (IsObject param2))
				(param2 cue: &rest)
			)
		)
		(return param1)
	)
)

(instance caveTalkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(KQ6Print
					font: userFont
					posn: -1 15
					say: 0 3 2 local0 1
					init: self
				)
			)
			(1
				(messager say: 3 2 local0 2 self oneOnly: 0)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego
					init:
					setStep: 5 2
					view: 390
					show:
					setLoop: -1
					setPri: -1
					setSpeed: 6
					normal: 0
					loop: 5
					cel: 4
					posn: 43 123
					setScale: 0
					actions: egoActions
					ignoreActors: 1
				)
				(= cycles 2)
			)
			(2
				(ego signal: (| (ego signal?) $1000) setCycle: BegLoop self)
			)
			(3 (= cycles 2))
			(4
				(ego
					view: 3902
					posn: 43 123
					setCycle: StopWalk 3903
					setSpeed: (ego currentSpeed?)
					setHeadingCode: myHeadingCode
				)
				(= cycles 2)
			)
			(5
				(ego looper: 0)
				(if (not (Btst 96)) (Bset 96) (theGame givePoints: 1))
				(messager say: 1 0 9 1 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance leaveHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 55 130 self)
			)
			(1
				(messager say: 11 5 13 1 self)
			)
			(2
				(if (OneOf (ego view?) 392 393)
					(ego
						view: 391
						loop: 3
						cel: 5
						setSpeed: 6
						setCycle: BegLoop self
					)
					(Palette palSET_INTENSITY 64 223 60)
				else
					(self cue:)
				)
			)
			(3 (= cycles 2))
			(4
				(ego
					view: 390
					cel: 0
					loop: 6
					normal: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(5
				(ego looper: 0 setHeadingCode: 0 hide:)
				(curRoom drawPic: 98 -32758)
				(= cycles 6)
			)
			(6
				(curRoom newRoom: 340)
				(self dispose:)
			)
		)
	)
)

(instance crawl2Cave2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 253 151 self)
			)
			(1
				(messager say: 12 5 11 1 self)
			)
			(2
				(ego
					normal: 0
					view: 391
					loop: 2
					cel: 5
					setSpeed: 6
					looper: 0
					setCycle: BegLoop self
				)
				(Palette palSET_INTENSITY 64 223 60)
			)
			(3
				(ego view: 390 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego
					view: 390
					setLoop: 7
					cel: 0
					posn: 247 149
					setCycle: Walk
					setPri: 2
					setMotion: MoveTo 288 135 self
				)
			)
			(5
				(ego setLoop: -1 setPri: -1 hide:)
				((curRoom obstacles?) delete: roomPoly)
				(curRoom drawPic: 98 10)
				(= seconds 2)
			)
			(6
				(= local0 12)
				(curRoom
					style: 16394
					drawPic: 390 16394
					addObstacle: (roomPoly points: @local18 yourself:)
				)
				(opening dispose:)
				(mintHole dispose:)
				(pepperBush addToPic:)
				(ledge addToPic:)
				(toCave1 init:)
				(rBlock addToPic:)
				(= cycles 2)
			)
			(7
				(Load rsMESSAGE 390)
				(ego
					show:
					posn: 49 138
					view: 3901
					loop: 7
					cel: 4
					setCycle: BegLoop self
				)
			)
			(8
				(ego
					setSpeed: (ego currentSpeed?)
					view: 3904
					loop: 0
					posn: 49 138
					setCycle: StopWalk 3905
				)
				(= cycles 10)
			)
			(9 (messager say: 1 0 2 1 self))
			(10
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance crawl2Cave1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 77 149 self)
			)
			(1
				(messager say: 11 5 12 1 self)
			)
			(2
				(ego
					normal: 0
					setSpeed: 6
					view: 3901
					loop: 6
					cel: 0
					posn: 78 147
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					setLoop: 8
					setCycle: Walk
					setPri: 2
					setMotion: MoveTo 29 133 self
				)
			)
			(4
				((curRoom obstacles?) delete: roomPoly)
				(ego setPri: -1 setLoop: -1 hide:)
				(rBlock dispose:)
				(toCave1 dispose:)
				(curRoom style: 10 drawPic: 98 -32758)
				(= seconds 1)
			)
			(5
				(curRoom
					drawPic: 390 100
					addObstacle: (roomPoly points: @local2 yourself:)
				)
				(Palette palSET_INTENSITY 64 223 60)
				(opening init:)
				(mintHole init:)
				(= cycles 2)
			)
			(6
				(ego show: view: 390 loop: 6 posn: 270 142 cel: 4)
				(= ticks 15)
			)
			(7 (ego setCycle: BegLoop self))
			(8 (= cycles 2))
			(9
				(ego
					view: 3902
					loop: 1
					setCycle: StopWalk 3903
					setSpeed: (ego currentSpeed?)
				)
				(= cycles 6)
			)
			(10
				(messager say: 1 0 3 1 self)
			)
			(11
				(theGame handsOn:)
				(= local0 10)
				(self dispose:)
			)
		)
	)
)

(instance lightItUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 2 20 10 1 self)
			)
			(1
				(= local0 11)
				(soundFx2 number: 932 setLoop: 1 play:)
				(ego
					view: 391
					loop: (+ (ego loop?) 2)
					cel: 0
					cycleSpeed: 21
					setCycle: EndLoop self
				)
				(if (Btst 48) (ego setScript: cyclePalette 0 0))
			)
			(2
				(if (not (Btst 97)) (Bset 97) (theGame givePoints: 2))
				(ego
					view: 392
					loop: (- (ego loop?) 2)
					setSpeed: (ego currentSpeed?)
					looper: mySmooper
					setCycle: StopWalk 393
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getLeaf of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (Bset 138)) (theGame givePoints: 1))
				(ego
					setSpeed: 7
					view: 3901
					loop: 0
					cel: -1
					get: 31
					setCycle: EndLoop self
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 3904
					loop: 0
					setSpeed: (ego currentSpeed?)
					setCycle: StopWalk 3905
				)
				(= cycles 2)
			)
			(3 (= ticks 20))
			(4 (messager say: 7 5 7 1 self))
			(5 (= cycles 2))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1 (return 0))
				(5 (return 0))
				(2 (return 0))
				(20
					(if (== local0 10)
						(curRoom setScript: lightItUp)
					else
						(messager say: 2 20 local0 1)
					)
					(return 1)
				)
				(else 
					(messager say: 0 0 134 1 0 899)
					(return 1)
				)
			)
		)
	)
)

(instance cyclePalette of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_INTENSITY 64 223 100)
				(= ticks 2)
			)
			(1
				(= local1 0)
				(self dispose:)
			)
		)
	)
)
