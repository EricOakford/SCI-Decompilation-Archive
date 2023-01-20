;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include sci.sh)
(use Main)
(use Intrface)
(use Arcada)
(use SQRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm12 0
)

(instance rm12 of SQRoom
	(properties
		lookStr {This is the cavernous Vehicle Bay. An escape pod rests on launch rails at the end of the platform. Bay doors at the end of the rails allow access to the emptiness beyond.}
		picture 12
		west 11
	)
	
	(method (init)
		(LoadMany 128 11 1 17 112)
		(Load rsSOUND 363)
		(self setRegions: 700)
		(super init:)
		(ego init:)
		(if (ArcadaCheck 551 1)
			(buttons setCycle: Fwd)
			(motors setCycle: Fwd)
		)
		(buttons init: stopUpd:)
		(motors init: stopUpd:)
		(if (not (Btst 10))
			(bayDoors init:)
		else
			(stars setCycle: Fwd init:)
		)
		(if (> selfDestructTimer 30)
			(tv init:)
			(if (>= (theGame detailLevel:) (tv detailLevel:))
				(tv setScript: tvScript)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 0
					init:
						0
						176
						32
						176
						111
						162
						186
						119
						180
						117
						214
						92
						209
						90
						175
						115
						151
						104
						89
						132
						12
						142
						0
						142
						0
						0
						319
						0
						319
						189
						0
						189
					yourself:
				)
		)
		(features add: monitorFeat podFeat eachElementDo: #init)
		(if (Btst 10)
			(features add: bayDoorFeat spaceFeat eachElementDo: #init)
		)
		(podDoor init: approachVerbs: 3 2)
		(switch prevRoomNum
			(13
				(podDoor cel: 0)
				(self setScript: exitPod)
				(ego
					view: 11
					posn: 179 123
					setPri: 0
					loop: 2
					cel: 0
					hide:
				)
			)
			(11
				(if (not (Btst 16))
					(ego cel: 0 posn: 18 169 view: 11 normal: 0)
					(self setScript: explode)
				else
					(ego illegalBits: -32768 posn: 16 157)
					(HandsOn)
				)
			)
			(else 
				(ego view: 1 posn: 16 157)
			)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((& (= temp0 (ego onControl: 1)) $0004) (self setScript: fallingScript))
			((& temp0 $0002)
				(self
					setScript: fallingScript 0 (if (>= (ego x?) 87) 7 else 6)
				)
			)
		)
		(super doit:)
	)
)

(instance tvScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (tv setCycle: Beg self))
			(1 (= seconds (Random 2 5)))
			(2
				(tv setCycle: Fwd)
				(= seconds (Random 1 4))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance fallingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					view: 17
					loop: register
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(1
				(ego setPri: (if register 3 else -1) setCycle: End self)
			)
			(2
				(soundFx number: 363 loop: 1 play: self)
				(ego
					setLoop: (+ (ego loop?) 2)
					setCycle: Fwd
					setStep: 3 13
					setMotion: MoveTo (ego x?) 250
				)
			)
			(3
				(= register 0)
				(EgoDead 939 0 0 12 0)
				(self dispose:)
			)
		)
	)
)

(instance enterPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(podDoor setPri: 7)
				(ego
					illegalBits: 0
					view: 11
					loop: 1
					setPri: 0
					cel: 0
					setCycle: End self
				)
			)
			(1
				(ego hide:)
				(soundFx number: 324 loop: 1 play:)
				(podDoor setCycle: Beg self)
			)
			(2
				(soundFx number: 369 loop: 1 play: self)
			)
			(3 (curRoom newRoom: 13))
		)
	)
)

(instance exitPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				(= cycles 3)
			)
			(1
				(soundFx number: 324 loop: 1 play:)
				(podDoor setCycle: End self)
			)
			(2
				(soundFx stop:)
				(ego show: setPri: 0 cycleSpeed: 12 setCycle: CT 6 1 self)
			)
			(3
				(ego setPri: -1 setCycle: End self)
			)
			(4
				(NormalEgo 1 1 61)
				(ego setMotion: MoveTo 166 124 self)
			)
			(5
				(ego illegalBits: -32768)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance explode of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(ego cycleSpeed: 8 setCycle: CT 4 1 self)
			)
			(2
				(soundFx number: 320 loop: 1 play:)
				(ego cycleSpeed: 25 setCycle: CT 7 1 self)
			)
			(3
				(soundFx number: 321 loop: 1 play:)
				(ego cycleSpeed: 8 setCycle: End self)
			)
			(4 (EgoDead 941 0 0 12 1))
		)
	)
)

(instance bayDoors of View
	(properties
		x 52
		y 99
		description {bay doors}
		sightAngle 45
		lookStr {The massive bay doors are tightly sealed.}
		view 112
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(Printf
					12
					2
					(if (Btst 10)
						{open wide leaving nothing but millions of kilometers of empty space between you and the very stars themselves}
					else
						{tightly sealed}
					)
				)
			)
			(3 (Print 12 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance podDoor of Prop
	(properties
		x 193
		y 78
		description {pod door}
		sightAngle 45
		approachX 172
		approachY 119
		lookStr {This is one of the Arcada's escape pods. You vaguely remember receiving some orientation on these when you joined the crew. Unfortunately, you were deep into research on sleeping with your eyes open and, hence, don't remember much.}
		view 112
		loop 1
		cel 5
		priority 2
		signal $0010
		cycleSpeed 5
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 3 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: enterPod)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tv of Prop
	(properties
		x 185
		y 81
		z 40
		description {tv}
		sightAngle 45
		view 112
		cel 1
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (> selfDestructTimer 30)
					(Print 12 4)
				else
					(Print 12 5)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance motors of Prop
	(properties
		x 299
		y 133
		description {motors}
		sightAngle 45
		lookStr {These powerful Pyron Weezpump drives propel the pod.}
		view 112
		loop 2
		cel 3
		cycleSpeed 8
		detailLevel 2
	)
)

(instance stars of Prop
	(properties
		x 36
		y 47
		description {star}
		sightAngle 45
		lookStr {Even though we all know that the empty atmosphere of space would not permit this twinkling effect, you have to admit it looks nice.}
		view 112
		loop 3
		cel 2
		cycleSpeed 12
		detailLevel 2
	)
)

(instance buttons of Prop
	(properties
		x 145
		y 68
		description {buttons}
		sightAngle 45
		lookStr {Through the cabin window you can see an array of displays and controls inside the pod.}
		view 112
		loop 5
		cel 1
		cycleSpeed 8
		detailLevel 2
	)
)

(instance bayDoorFeat of Feature
	(properties
		x 53
		y 50
		nsTop -1
		nsBottom 102
		nsRight 106
		description {bay door}
		sightAngle 45
		onMeCheck $0010
		lookStr {Beyond the yawning doors lies the serenity of deep space.}
	)
)

(instance spaceFeat of Feature
	(properties
		x 53
		y 50
		nsTop -1
		nsBottom 102
		nsRight 106
		description {space}
		sightAngle 45
		onMeCheck $0020
		lookStr {Beyond the yawning doors lies the serenity of deep space.}
	)
)

(instance monitorFeat of Feature
	(properties
		x 194
		y 111
		z 90
		nsLeft 164
		nsBottom 43
		nsRight 225
		description {monitor}
		sightAngle 45
		onMeCheck $0040
		lookStr {There's that butt-ugly green guy again. Of all the systems destroyed thus far, you wish the teleview would have been one of them.}
	)
	
	(method (doVerb theVerb)
		(tv doVerb: theVerb &rest)
	)
)

(instance podFeat of Feature
	(properties
		x 224
		y 97
		description {pod}
		sightAngle 45
		onMeCheck $0008
		approachX 172
		approachY 119
		lookStr {A giant, silver, pill-shaped pod sits poised to fire, much like a bullet in the chamber of an ancient pistol.}
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 3 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: enterPod)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
