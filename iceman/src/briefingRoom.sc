;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use Intrface)
(use washington)
(use InitAllFeatures)
(use GoToSaid)
(use LoadMany)
(use DPath)
(use RFeature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	briefingRoom 0
)

(local
	chair2
	chair3
)
(instance briefingRoom of Room
	(properties
		picture 21
		vanishingX 153
		vanishingY -20
	)
	
	(method (init)
		(super init:)
		(self setRegions: 302)
		(LoadMany VIEW 204 421 21 121 221 421 521)
		(slideShowWest init:)
		(slideShowEast init:)
		(Braxton init:)
		(Agent init:)
		(envelope init: setPri: 9)
		(coffeeCup init:)
		(HandsOff)
		(chair init:)
		((= chair2 (Clone chair)) x: 156 y: 144 init:)
		((= chair3 (Clone chair)) x: 95 y: 145 init:)
		(flag init:)
		(addToPics doit:)
		(ego
			posn: 270 206
			view: 204
			loop: 3
			init:
			setScript: egoEnters
		)
		(self setScript: briefingScript setFeatures: table)
		(InitAllFeatures)
		(globalSound
			number: (SoundFX 63)
			priority: 1
			loop: -1
			play:
		)
	)
	
	(method (dispose)
		(cls)
		(globalSound fade:)
		(washington beenBriefed: TRUE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<around][/room]')
				(Print 21 0)
			)
			((Said 'stand,(get<up)')
				(if (== (ego view?) 204)
					(Print 21 1)
				else
					(ego setScript: egoStandScript)
				)
			)
			((Said 'drink,get/drink,cup,coffee,water')
				(Print 21 2)
			)
			((Said 'open/door')
				(Print 21 3)
			)
		)
	)
)

(instance slideShowWest of Prop
	(properties
		y 103
		x 119
		view 21
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 8 stopUpd: setCel: 0)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance slideShowEast of Prop
	(properties
		y 103
		x 215
		view 21
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 8 stopUpd: setCel: 0)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance slideWest of View
	(properties
		y 103
		x 119
		view 21
		loop 6
		cel 4
	)
	
	(method (init)
		(super init:)
		(self setPri: 7)
	)
)

(instance slideEast of View
	(properties
		y 103
		x 215
		view 21
		loop 6
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 7)
	)
)

(instance blackOutSlide of View
	(properties
		y 103
		x 119
		view 21
		loop 6
	)
	
	(method (init)
		(super init:)
		(self setPri: 8)
	)
)

(instance briefingScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 40))
			(1
				(lips init: setCycle: Forward)
				(Print 21 4
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(2
				(cls)
				(Print 21 5
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(3
				(lips dispose:)
				(cls)
				(Braxton illegalBits: 0 setMotion: MoveTo 165 123 self)
				(ego
					setMotion: MoveTo (- (chair x?) 30) (chair y?) self
					ignoreControl: cWHITE
					ignoreActors:
				)
			)
			(4 (Braxton loop: 2))
			(5
				(Braxton stopUpd:)
				(ego
					setMotion: MoveTo (- (chair x?) 10) (- (chair y?) 1) self
				)
			)
			(6
				(ego
					view: 421
					setLoop: 0
					posn: (chair x?) (chair y?)
					cycleSpeed: 1
					heading: 0
					setCycle: EndLoop self
				)
			)
			(7
				(ego stopUpd:)
				(lips init: posn: 166 86 setCycle: Forward)
				(Print 21 6
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(8
				(cls)
				(Print 21 7
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(9
				(cls)
				(Print 21 8
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(10
				(lips dispose:)
				(cls)
				(coffeeCup dispose:)
				(Braxton
					view: 521
					setLoop: 0
					cycleSpeed: 1
					posn: (Braxton x?) (- (Braxton y?) 2)
					setCycle: EndLoop self
				)
				(= seconds 5)
			)
			(11
				(Braxton setCycle: CycleTo 2 -1)
				(= seconds 3)
			)
			(12
				(Braxton setCycle: EndLoop)
				(= seconds 3)
			)
			(13
				(Braxton setCycle: BegLoop self)
			)
			(14
				(coffeeCup init:)
				(Braxton
					view: 121
					loop: 2
					cel: 5
					posn: (Braxton x?) (+ (Braxton y?) 2)
				)
				(Print 21 9
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(15
				(lips init: posn: 166 87 setCycle: Forward)
				(Print 21 10
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(16
				(cls)
				(Print 21 11
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(17
				(cls)
				(Print 21 12
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 17)
			)
			(18
				(cls)
				(Print 21 13
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(19
				(cls)
				(Print 21 14
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(20
				(cls)
				(Print 21 15
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(21
				(cls)
				(lips dispose:)
				(coffeeCup dispose:)
				(Braxton view: 521 setLoop: 0 cycleSpeed: 1 setCycle: EndLoop)
				(= seconds 3)
			)
			(22
				(Braxton setCycle: CycleTo 2 -1)
				(= seconds 3)
			)
			(23
				(Braxton setCycle: EndLoop)
				(= seconds 2)
			)
			(24
				(Braxton setCycle: BegLoop self)
			)
			(25
				(coffeeCup init:)
				(lips init: setCycle: Forward)
				(Braxton
					view: 121
					cycleSpeed: 0
					setLoop: 2
					cel: 5
					setCycle: Walk
				)
				(Print 21 16
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
					#draw
				)
				(= seconds 14)
			)
			(26
				(cls)
				(lips dispose:)
				(Braxton setLoop: -1 setMotion: MoveTo 58 123 self)
			)
			(27
				(Agent illegalBits: 0 setCycle: BegLoop self)
				(Braxton setScript: braxSitScript)
			)
			(28
				(Agent
					view: 221
					posn: (- (chair2 x?) 10) (- (chair2 y?) 1)
					setLoop: -1
					setCycle: Walk
					setMotion: DPath 91 150 58 150 58 123 165 123 self
				)
			)
			(29
				(Agent loop: 2)
				(blackOutSlide init:)
				(slideShowWest setCycle: EndLoop self)
			)
			(30
				(Agent stopUpd:)
				(slideShowWest cue:)
				(= cycles 10)
			)
			(31
				(blackOutSlide hide:)
				(slideWest setCel: 4 init:)
				(Agent setLoop: 5 setCel: 0 setCycle: EndLoop)
				(= cycles 5)
			)
			(32
				(lips init: posn: 164 87 setCycle: Forward)
				(Print 21 17
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 18)
			)
			(33 (Agent setCycle: BegLoop self))
			(34
				(Agent setLoop: 2 setCel: 0)
				(lips hide:)
				(cls)
				(Print 21 18
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(blackOutSlide
					posn: (slideEast x?) (slideEast y?)
					show:
				)
				(slideShowEast setCycle: EndLoop self)
			)
			(35
				(lips show:)
				(slideShowEast cue:)
				(= seconds 2)
			)
			(36
				(blackOutSlide hide:)
				(slideEast setCel: 1 init:)
				(Agent setLoop: 4 setCycle: EndLoop)
				(lips x: 167)
				(= seconds 10)
			)
			(37
				(cls)
				(Print 21 19
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 15)
			)
			(38
				(Agent setCycle: BegLoop)
				(= seconds 5)
			)
			(39
				(lips dispose:)
				(cls)
				(Print 21 20 #title {Agent} #at 5 10 #dispose)
				(blackOutSlide
					posn: (slideWest x?) (slideWest y?)
					show:
				)
				(= seconds 5)
			)
			(40
				(blackOutSlide hide:)
				(slideWest loop: 5 setCel: 0 init:)
				(= cycles 10)
			)
			(41
				(slideWest loop: 6 setCel: 2 init:)
				(Agent setLoop: 5 setCycle: EndLoop)
				(= cycles 10)
			)
			(42
				(lips init: x: 164 setCycle: Forward)
				(Print 21 21
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(43
				(cls)
				(lips dispose:)
				(Agent setCycle: BegLoop)
				(Print 21 20 #title {Agent} #at 5 10 #dispose)
				(blackOutSlide
					posn: (slideEast x?) (slideEast y?)
					show:
				)
				(= seconds 12)
			)
			(44
				(Agent setLoop: -1 loop: 2 setCycle: Walk)
				(blackOutSlide hide:)
				(slideEast loop: 6 setCel: 3)
				(= seconds 2)
			)
			(45
				(lips init: x: 166 setCycle: Forward)
				(Print 21 22
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(46
				(cls)
				(Print 21 23
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(47
				(cls)
				(Print 21 24
					#title {Agent}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(slideShowWest setCycle: BegLoop)
				(= seconds 10)
			)
			(48
				(slideShowEast setCycle: BegLoop self)
				(cls)
			)
			(49
				(lips dispose:)
				(slideShowWest cue:)
				(slideShowEast cue:)
				(Braxton setCycle: BegLoop self)
			)
			(50
				(Agent setScript: agentSitScript)
				(Braxton
					view: 121
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 0
					setMotion: DPath 56 138 56 123 165 123 self
				)
			)
			(51
				(lips init: posn: 166 87 setCycle: Forward)
				(Braxton loop: 2 cel: 5)
				(Print 21 25
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 15)
			)
			(52
				(cls)
				(Print 21 26
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(53
				(cls)
				(Print 21 27
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(54
				(cls)
				(Print 21 28
					#title {Braxton}
					#at 5 10
					#width 305
					#font 3
					#dispose
				)
				(= seconds 20)
			)
			(55
				(cls)
				(lips dispose:)
				(Agent setCycle: BegLoop self)
			)
			(56
				(Braxton
					illegalBits: 0
					setLoop: -1
					ignoreActors: TRUE
					setMotion: DPath 40 123 40 154 95 158 224 159 265 165 267 240 self
				)
				(Agent
					view: 221
					setCycle: Walk
					ignoreActors: TRUE
					illegalBits: 0
					setLoop: -1
					setMotion: DPath 156 158 224 159 265 165 267 240 self
				)
			)
			(57)
			(58
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
	
	(method (cue)
		(= cycles (= seconds 0))
		(super cue:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(OneOf state 47 48)
				(!= (event type?) keyDown)
				(!= (event message?) ENTER)
				(event claimed?)
			)
			(return)
		)
		(if (and (not (OneOf state 29 34)) modelessDialog)
			(modelessDialog dispose:)
			(self cue:)
		)
		(event claimed: TRUE)
	)
)

(instance Braxton of Actor
	(properties
		y 123
		x 144
		view 121
		loop 2
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: ignoreControl: cWHITE setCycle: Walk)
	)
)

(instance braxSitScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Braxton setMotion: MoveTo 48 123 self)
			)
			(1
				(Braxton
					ignoreControl: cWHITE
					ignoreActors:
					setMotion: MoveTo 85 144 self
				)
			)
			(2
				(Braxton
					cycleSpeed: 2
					posn: 95 145
					view: 421
					setLoop: 2
					cel: 0
					setCycle: EndLoop
				)
			)
		)
	)
)

(instance Agent of Actor
	(properties
		y 144
		x 156
		view 421
		loop 1
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors: TRUE
			ignoreControl: cWHITE
			cel: (self lastCel:)
		)
	)
)

(instance chair of RPicView
	(properties
		y 146
		x 231
		view 21
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/chair]>')
				(cond 
					((Said 'sit')
						(DontNeedTo)
					)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 21 29)
					)
				)
			)
		)
	)
)

(instance flag of RPicView
	(properties
		y 127
		x 275
		view 21
		loop 3
		cel 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/flag]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 21 30)
					)
					((Said 'salute')
						(if (== (ego view?) 204)
							(Print 21 31)
						else
							(Print 21 32)
						)
					)
				)
			)
		)
	)
)

(instance agentSitScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Agent setMotion: DPath 58 123 58 140 91 150 self)
			)
			(1
				(Agent
					setMotion: MoveTo (- (chair2 x?) 10) (- (chair2 y?) 1) self
				)
			)
			(2
				(Agent
					view: 421
					loop: 1
					posn: (chair2 x?) (chair2 y?)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3 (curRoom cue:))
		)
	)
)

(instance coffeeCup of View
	(properties
		y 109
		x 163
		view 21
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 10)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/coffee,cup'))
			((Said 'look[<at]/coffee,cup')
				(Print 21 33)
			)
		)
	)
)

(instance lips of Prop
	(properties
		y 86
		x 145
		view 521
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setLoop: loop setPri: 9)
	)
)

(instance egoStandScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCycle: BegLoop self)
			)
			(1
				(ego
					view: 204
					loop: 3
					posn: (- (chair x?) 10) (- (chair y?) 1)
					setCycle: Walk
					observeControl: cWHITE
					ignoreActors: 0
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: -1
				)
				(HandsOn)
			)
		)
	)
)

(instance envelope of View
	(properties
		y 110
		x 204
		view 21
		loop 1
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/[envelope,order,instructions]>')
				(cond 
					((Said 'drop')
						(if (ego has: iEnvelope)
							(Print 21 34)
						else
							(Print 21 35)
						)
					)
					((and (ego has: iEnvelope) (Said 'open,look[<at,in]'))
						(Print 21 36)
					)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 21 37)
					)
					((Said 'open,look[<in]')
						(Print 21 38)
					)
					((and (ego has: iEnvelope) (Said 'get'))
						(Print 21 39)
					)
					((GoToIfSaid self event envelope 40 'get' 21 40))
					((Said 'get')
						(Print 21 41)
						(envelope hide:)
						(ego get: iEnvelope)
						(theGame changeScore: 1)
					)
				)
			)
		)
	)
)

(instance egoEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					loop: 3
					illegalBits: 0
					setMotion: MoveTo 230 160 self
				)
			)
			(1
				(ego setMotion: MoveTo 215 160 self)
			)
			(2
				(ego illegalBits: cWHITE loop: 3)
				(briefingRoom south: 20)
			)
		)
	)
)

(instance table of RFeature
	(properties
		y 110
		x 165
		nsTop 108
		nsLeft 70
		nsBottom 112
		nsRight 250
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/table]>')
				(cond 
					((TurnIfSaid self event 'look[<on,at]/*'))
					((Said 'look[<on,at]')
						(if (ego has: iEnvelope)
							(Print 21 42)
						else
							(Print 21 43)
						)
					)
				)
			)
		)
	)
)
