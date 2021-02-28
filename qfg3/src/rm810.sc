;;; Sierra Script 1.0 - (do not remove this comment)
(script# 810)
(include sci.sh)
(use Main)
(use Target)
(use EgoDead)
(use JumpX)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm810 0
)

(local
	local0
	local1
	egoMoveSpeed
)
(procedure (localproc_0d92)
	(sFx number: 931 play:)
	(deader init: setPri: 11 ignoreActors: 1 addToPic:)
	(switch global448
		(0 0)
		(1
			(deader1 init: ignoreActors: 1 addToPic:)
		)
		(2
			(deader1 init: ignoreActors: 1 addToPic:)
			(deader2 init: ignoreActors: 1 addToPic:)
		)
		(else 
			(deader1 init: ignoreActors: 1 addToPic:)
			(deader2 init: ignoreActors: 1 addToPic:)
			(deader3 init: ignoreActors: 1 addToPic:)
		)
	)
	(return (++ global448))
)

(instance rm810 of Rm
	(properties
		noun 9
		picture 810
	)
	
	(method (init)
		(Scaler backY: 145)
		(ego
			x: -22
			y: 168
			init:
			setScale: Scaler 100 50 189 125
			normalize:
		)
		(LoadMany 128 810 570 6)
		(HandsOn)
		(super init:)
		(cSound number: 810 setLoop: -1 play: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						173
						81
						173
						165
						189
						216
						148
						185
						138
						168
						135
						155
						127
						133
						124
						133
						99
						112
						99
						112
						126
						142
						157
						0
						157
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 148 81 212 81 212 71 148 71
					yourself:
				)
		)
		(pedestal init:)
		(stairs init:)
		(leftStairs init:)
		(ruins init:)
		(rocks init:)
		(noWayToGo init:)
		(anubisStatue init:)
		(if (and (not (ego has: 34)) (not (Btst 136)))
			(opal setPri: 14 init: stopUpd:)
		)
		(if (Btst 136)
			(doorWay init: loop: 2 approachVerbs: 4 1 ignoreActors: 0)
		else
			(doorWay init: approachVerbs: 4 1)
		)
		(if
		(and (not (== global155 0)) (== prevRoomNum 550))
			(ego x: 60 y: 169 code: quikChek)
			(localproc_0d92)
		else
			(self setScript: egoEnters)
		)
		(if (and (== prevRoomNum 550) (== global155 0))
			(self setScript: egoIsDead)
		)
	)
	
	(method (dispose)
		(ego code: 0)
		(UnLoad 128 810)
		(UnLoad 128 570)
		(UnLoad 128 6)
		(LoadMany 0 956 923 37 57)
		(if gNewList (gNewList eachElementDo: #dispose))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81
				(ego setScript: castProj 0 theVerb)
			)
			(83
				(ego setScript: castProj 0 theVerb)
			)
			(32
				(ego setScript: castProj 0 theVerb)
			)
			(20
				(ego setScript: castProj 0 theVerb)
			)
			(65 (messager say: 1 6 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setInset param1 param2 param3)
		(ego code: 0)
		(theIconBar disable: 6 1 5 3)
		(if inset (inset dispose:))
		(if (and argc param1)
			(param1
				init:
					(if (>= argc 2) param2 else 0)
					self
					(if (>= argc 3) param3 else 0)
			)
		)
		(if (Btst 136)
			(opal view: 810 loop: 1 setPri: 14 x: 158 y: 83 init:)
		)
	)
)

(instance egoIsDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1 (EgoDead))
		)
	)
)

(instance castOpenOnDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(AutoTarget 125 101)
				(self setScript: (ScriptID 13 0) self)
			)
			(1 (messager say: 1 6 6))
			(2
				(HandsOn)
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance egoFalls of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					code: 0
					view: 35
					setLoop: 3
					cel: 0
					setCycle: CT 1 1 self
				)
				(= egoMoveSpeed (ego moveSpeed?))
			)
			(1
				(ego
					moveSpeed: 0
					ignoreActors: 1
					illegalBits: 0
					setMotion: JumpX -48 self
				)
			)
			(2
				(ego x: 162 y: 131 z: 0 setCycle: End self)
			)
			(3
				(sFx number: 920 setLoop: 1 play:)
				(ShakeScreen 10)
				(= cycles 11)
			)
			(4
				(ego
					loop: 5
					cel: 0
					illegalBits: -32768
					setCycle: End self
				)
			)
			(5
				(if
				(or (cast contains: apeMan) (cast contains: apeMan1))
					(= monsterNum 575)
					(= monsterHealth 180)
					(curRoom newRoom: 550)
				)
				(ego code: quikChek moveSpeed: egoMoveSpeed normalize:)
				(= local0 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cantExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 code: 0)
				(apeMan1
					setScale: Scaler 100 50 189 125
					x: 0
					y: 168
					init:
					setStep: 4 4
					setCycle: Walk
					ignoreActors: 1
					setMotion: MoveTo 30 168 self
				)
			)
			(1
				(= monsterNum 575)
				(= monsterHealth 180)
				(curRoom newRoom: 550)
			)
		)
	)
)

(instance apeManCrossLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(apeMan
					x: 322
					y: 79
					setPri: 0
					setScale: Scaler 100 50 189 50
					setCycle: Walk
					init:
					setStep: 4 4
					setMotion: MoveTo 96 79 self
				)
			)
			(1
				(apeMan setHeading: 180)
				(= seconds 5)
			)
			(2
				(apeMan setMotion: MoveTo 322 79 self)
			)
			(3
				(apeMan setScale: 189 setPri: -1 dispose:)
				(self dispose:)
			)
		)
	)
)

(instance castProj of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: (ScriptID 32 0) self register)
			)
			(1
				(if (cast contains: apeMan)
					(= monsterNum 575)
					(= monsterHealth 180)
					(curRoom newRoom: 550)
				)
				(ego normalize:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance apeManCrossRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(apeMan
					setScale: Scaler 100 50 189 125
					x: 41
					y: 134
					init:
					setStep: 4 4
					setCycle: Walk
					setMotion: MoveTo 340 162 self
				)
			)
			(1
				(apeMan dispose:)
				(self dispose:)
			)
		)
	)
)

(instance climbDownPedestal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 166 70 self)
			)
			(1
				(sFx number: 928 play:)
				(ego view: 7 cel: 11 loop: 4 setCycle: Beg self)
			)
			(2
				(ego
					code: quikChek
					view: 7
					y: 93
					setStep: 2 1
					setLoop: 3
					setCycle: Rev
					setMotion: MoveTo 162 131 self
				)
			)
			(3
				(ego view: 5 normalize:)
				(HandsOn)
				(= local0 0)
				(if (not (cast contains: apeMan))
					(curRoom setScript: apeManCrossLeft)
				else
					(= monsterNum 575)
					(= monsterHealth 180)
					(curRoom newRoom: 550)
				)
				(self dispose:)
			)
		)
	)
)

(instance climbUpPedestal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 162 131 self)
			)
			(1
				(ego view: 7 setLoop: 3 setCycle: ForwardCounter 2 self)
				(sFx number: 928 play:)
			)
			(2
				(ego
					setCycle: Fwd
					cycleSpeed: 5
					setStep: 2 1
					setMotion: MoveTo 166 93 self
				)
			)
			(3
				(ego loop: 4 cel: 0 y: 70 setCycle: End self)
			)
			(4
				(ego view: 13 setHeading: 180 setCycle: End)
				(= seconds 3)
			)
			(5
				(ego code: fallChek setStep: 3 2 normalize:)
				(= local0 1)
				(HandsOn)
				(if (not (cast contains: apeMan))
					(apeMan
						x: 41
						y: 144
						setScale: 189
						init:
						setStep: 4 4
						setScript: apeManCrossRight
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(apeMan
					setScale: Scaler 100 50 189 125
					init:
					setStep: 4 4
					setCycle: Walk
					setMotion: MoveTo 340 162 self
				)
			)
			(1
				(apeMan dispose:)
				(ego x: -20 y: 168 setMotion: PolyPath 60 169 self)
			)
			(2
				(if (Btst 150)
					(messager say: 1 6 7 0 self)
				else
					(self cue:)
				)
			)
			(3
				(ego code: quikChek)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance hoarkOpal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					get: 34
					solvePuzzle: 329 5 5
					setMotion: MoveTo 181 71 self
				)
			)
			(1
				(opal dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance apeMan1 of Actor
	(properties
		view 570
	)
)

(instance apeMan of TargActor
	(properties
		x 41
		y 134
		view 570
	)
	
	(method (getHurt)
		(= monsterNum 575)
		(= monsterHealth 180)
		(curRoom newRoom: 550)
		(super getHurt:)
	)
)

(instance doorWay of View
	(properties
		x 116
		y 94
		noun 8
		nsTop 87
		nsLeft 114
		nsBottom 121
		nsRight 132
		sightAngle 40
		approachX 124
		approachY 137
		_approachVerbs 4
		view 811
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local0 0)
					(if (not (Btst 136))
						(curRoom setInset: anubisInset)
					else
						(super doVerb: theVerb)
					)
				else
					(messager say: 1 6 2)
				)
			)
			(1
				(if (== local0 0)
					(curRoom setInset: anubisInset)
				else
					(messager say: 1 6 3)
				)
			)
			(75
				(if (not (ego script?))
					(ego setScript: castOpenOnDoor)
				)
			)
			(45
				(if (== local0 0)
					(curRoom setInset: anubisInset)
				else
					(messager say: 1 6 2)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance opal of View
	(properties
		x 185
		y 56
		view 811
		signal $4000
	)
)

(instance deader of View
	(properties
		x 46
		y 189
		view 572
		loop 1
	)
)

(instance deader1 of View
	(properties
		x 114
		y 86
		view 572
		loop 1
		cel 1
		scaleSignal $0001
		scaleX 81
		scaleY 81
	)
)

(instance deader2 of View
	(properties
		x 257
		y 75
		view 572
		loop 1
		cel 2
		scaleSignal $0001
		scaleX 110
		scaleY 110
	)
)

(instance deader3 of View
	(properties
		x 227
		y 172
		view 572
		loop 1
		cel 4
		scaleSignal $0001
		scaleX 111
		scaleY 111
	)
)

(instance anubisStatue of Feature
	(properties
		x 193
		y 57
		noun 2
		nsTop 46
		nsLeft 174
		nsBottom 69
		nsRight 212
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (cast contains: opal)
					(messager say: 10 1)
				else
					(messager say: 1 6 4)
				)
			)
			(4
				(if
					(and
						(== local0 1)
						(not (Btst 136))
						(not (ego has: 34))
					)
					(ego setScript: hoarkOpal)
				)
			)
			(82
				(if (not (ego has: 34))
					(if (== local0 1)
						(messager say: 1 6 5)
					else
						(AutoTarget 186 53)
						(curRoom setScript: (ScriptID 37 0) 0 anubisStatue)
					)
				)
			)
			(-82
				(opal dispose:)
				(ego get: 34 solvePuzzle: 329 5 6)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pedestal of Feature
	(properties
		x 168
		y 94
		noun 2
		nsTop 71
		nsLeft 150
		nsBottom 118
		nsRight 186
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== local0 0)
					(ego setScript: climbUpPedestal)
				else
					(ego setScript: climbDownPedestal)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stairs of Feature
	(properties
		x 233
		y 104
		noun 3
		nsTop 75
		nsLeft 215
		nsBottom 133
		nsRight 252
		sightAngle 180
	)
)

(instance leftStairs of Feature
	(properties
		x 73
		y 102
		noun 4
		nsTop 82
		nsLeft 53
		nsBottom 122
		nsRight 93
		sightAngle 180
	)
)

(instance ruins of Feature
	(properties
		x 123
		y 47
		noun 5
		nsTop 31
		nsLeft 87
		nsBottom 63
		nsRight 160
		sightAngle 180
	)
)

(instance rocks of Feature
	(properties
		x 43
		y 122
		noun 6
		nsTop 87
		nsLeft 3
		nsBottom 158
		nsRight 83
		sightAngle 180
	)
)

(instance noWayToGo of Feature
	(properties
		x 280
		y 151
		noun 7
		nsTop 114
		nsLeft 242
		nsBottom 189
		nsRight 319
		sightAngle 180
	)
)

(instance sFx of Sound
	(properties)
)

(instance fallChek of Code
	(properties)
	
	(method (doit)
		(if
		(or (ego inRect: 156 73 218 76) (< (ego x?) 156))
			(ego setScript: egoFalls)
		)
	)
)

(instance quikChek of Code
	(properties)
	
	(method (doit)
		(cond 
			((ego inRect: 0 152 36 200) (ego setScript: cantExit))
			((and (Btst 136) (ego inRect: 115 118 134 120))
				(= monsterNum 575)
				(= monsterHealth 180)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance anubisInset of Inset
	(properties
		view 810
		x 160
		y 135
		disposeNotOnMe 1
		noun 11
	)
	
	(method (dispose)
		(ego code: quikChek)
		(theIconBar enable: 6 1 5 3)
		(if (Btst 136) (opal dispose:))
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(45
				(ego drop: 34 solvePuzzle: 330 8)
				(opal view: 810 loop: 1 setPri: 14 x: 158 y: 83 init:)
				(Animate (cast elements?) 1)
				(doorWay loop: 2 ignoreActors: 1 approachY: 110)
				(Bset 136)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
