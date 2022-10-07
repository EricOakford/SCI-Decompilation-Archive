;;; Sierra Script 1.0 - (do not remove this comment)
(script# 344)
(include sci.sh)
(use Main)
(use rSacred)
(use KQ6Print)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	proc344_0 0
	proc344_1 1
	nightMare 2
	blowinIt 3
)

(local
	local0
	egoX
	egoY
)
(procedure (proc344_0)
	(nightMare init:)
)

(procedure (proc344_1)
	(if (& ((inventory at: 11) state?) $0008)
		(nightMare setScript: catchNiteMare)
	else
		(nightMare setScript: coldEmbers)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance nightMare of Actor
	(properties
		x 265
		y 112
		noun 10
		view 335
		signal $6000
	)
	
	(method (init)
		(rSacred marePresent: 1)
		(self cycleSpeed: 10 setCycle: Forward)
		(super init:)
	)
	
	(method (doit)
		(if
			(and
				(< (ego distanceTo: self) 65)
				(== (self loop?) 0)
				(not (self script?))
				(!= (curRoom script?) mareFlyAway)
			)
			(theGame handsOff:)
			(curRoom setScript: mareFlyAway 0 3)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr 59)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(= egoX (ego x?))
		(= egoY (ego y?))
		(switch theVerb
			(13
				(self setScript: reflectMare)
			)
			(28
				(theGame handsOff:)
				(curRoom setScript: 190)
			)
			(31 (self setScript: blowinIt))
			(5
				(self setScript: grabForMare)
			)
			(51
				(self setScript: alasPoorYorick)
			)
			(37
				(self setScript: giveHorseBird)
			)
			(19
				(super doVerb: theVerb &rest)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(14
				(super doVerb: theVerb &rest)
			)
			(16
				(super doVerb: theVerb &rest)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(32
				(super doVerb: theVerb &rest)
			)
			(12
				(super doVerb: theVerb &rest)
			)
			(44
				(self setScript: offerItem 0 theVerb)
			)
			(20
				(self setScript: offerItem 0 theVerb)
			)
			(30
				(self setScript: offerItem 0 theVerb)
			)
			(47
				(self setScript: offerItem 0 theVerb)
			)
			(65
				(self setScript: offerItem 0 theVerb)
			)
			(67
				(self setScript: offerItem 0 theVerb)
			)
			(68
				(self setScript: offerItem 0 theVerb)
			)
			(33
				(self setScript: offerItem 0 theVerb)
			)
			(70
				(self setScript: offerItem 0 theVerb)
			)
			(else 
				(self setScript: offerItem 0 0)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 210
		y 92
		view 336
		loop 9
		priority 15
		signal $4010
		cycleSpeed 10
	)
)

(instance theSkull of View
	(properties
		x 220
		y 126
		view 336
		loop 4
		priority 1
		signal $4010
	)
)

(instance reflectMare of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 336
					setLoop: 0
					cel: 0
					normal: 0
					posn: (+ (ego x?) 14) (+ (ego y?) 7)
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(messager say: 10 13 0 1 self 340)
			)
			(2
				(soundFx2 number: 346 setLoop: 1 play:)
				(nightMare setLoop: 0 setCycle: BegLoop self)
			)
			(3
				(nightMare cel: 5)
				(= ticks 6)
			)
			(4
				(nightMare cel: 0)
				(= ticks 6)
			)
			(5
				(nightMare cel: 5)
				(= ticks 6)
			)
			(6
				(nightMare cel: 0)
				(= ticks 6)
			)
			(7
				(messager say: 10 13 0 2 self 340)
			)
			(8
				(client setScript: mareFlyAway 0 13)
			)
		)
	)
)

(instance alasPoorYorick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 336
					setLoop: 0
					cel: 0
					normal: 0
					cycleSpeed: 6
					posn: (+ (ego x?) 14) (+ (ego y?) 7)
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(messager say: 10 51 0 1 self 340)
			)
			(2
				(ego setLoop: 8)
				(nightMare setCycle: BegLoop self)
			)
			(3
				(soundFx2 number: 346 setLoop: 1 play:)
				(nightMare setCycle: CycleTo 5 1)
				(= seconds 4)
			)
			(4
				(nightMare setCycle: Forward)
				(messager say: 10 51 0 2 self 340)
			)
			(5
				(ego setLoop: 0 cel: 2 setCycle: BegLoop self)
			)
			(6
				(ego posn: egoX egoY reset: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveHorseBird of Script
	(properties)
	
	(method (dispose)
		(if (not (== (birdSound prevSignal?) -1))
			(birdSound stop:)
		)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normal: 0
					view: 883
					posn: (- (ego x?) 5) (ego y?)
					loop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: Forward
				)
				(birdSound number: 930 loop: -1 play:)
				(= seconds 5)
			)
			(1
				(birdSound stop:)
				(messager say: 10 37 0 1 self 340)
			)
			(2
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3
				(birdSound number: 931 loop: -1 play:)
				(= seconds 7)
			)
			(4
				(birdSound stop:)
				(messager say: 10 37 0 2 self 340)
			)
			(5
				(ego loop: 2 cel: (ego lastCel:) setCycle: BegLoop self)
			)
			(6
				(ego posn: egoX egoY reset: 1)
				(= cycles 6)
			)
			(7
				(ego setHeading: 45)
				(= cycles 6)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance offerItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 336
					setLoop: 0
					cel: 0
					normal: 0
					posn: (+ (ego x?) 14) (+ (ego y?) 7)
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(messager say: 10 register 0 1 self 340)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(ego posn: egoX egoY reset: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance blowinIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 31 0 1 self 340)
			)
			(1
				(nightMare setCycle: BegLoop)
				(self setScript: (ScriptID 85 0) self)
			)
			(2
				(soundFx2 number: 346 setLoop: 1 play:)
				(nightMare setCycle: CycleTo 5 1 self)
			)
			(3 (= seconds 5))
			(4
				(messager say: 10 31 0 2 self 340)
			)
			(5
				(theGame handsOn:)
				(nightMare setCycle: Forward)
				(self dispose:)
			)
		)
	)
)

(instance grabForMare of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 10 5 0 1 self 340)
			)
			(1
				(soundFx2 number: 346 setLoop: 1 play:)
				(nightMare setCycle: BegLoop)
				(ego
					setMotion: PolyPath (- (nightMare x?) 20) (+ (nightMare y?) 20) self
				)
			)
			(2 (= cycles 4))
			(3
				(theGame handsOn:)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(client setScript: mareFlyAway 0 5)
			)
		)
	)
)

(instance mareFlyAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 3)
					(myConv add: 340 3 3 19 1 add: 340 3 3 19 2 init: self)
				else
					(self cue:)
				)
			)
			(1
				(soundFx2 number: 346 setLoop: 1 play:)
				(nightMare
					view: 335
					setLoop: 1
					cel: 0
					cycleSpeed: 12
					setPri: 8
					setCycle: EndLoop self
				)
			)
			(2
				(switch register
					(13
						(messager say: 10 13 0 3 self 340)
					)
					(5
						(myConv add: 340 10 5 0 2 add: 340 10 5 0 3 init: self)
					)
					(3
						(messager say: 3 3 19 3 self 340)
					)
					(else 
						(messager say: 10 0 0 1 self 340)
					)
				)
			)
			(3
				(theGlobalSound fade: 0 20 5)
				(ego reset: 6)
				(rSacred marePresent: 0)
				(nightMare dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance coldEmbers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 193 131 self)
			)
			(1
				(ego setHeading: 45)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(= cycles 6)
			)
			(2
				(ego
					view: 336
					normal: 0
					setLoop: 0
					cel: 0
					cycleSpeed: 18
					posn: (+ (ego x?) 14) (+ (ego y?) 7)
					setCycle: Forward
				)
				(= seconds 5)
			)
			(3
				(messager say: 1 0 4 1 self 340)
			)
			(4
				(KQ6Print
					say: 0 1 0 4 2
					posn: 10 10
					ticks: 120
					modeless: 1
					init:
				)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(6
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(7
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(8
				(ego setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(9
				(ego setLoop: 8)
				(= seconds 1)
			)
			(10
				(nightMare setCycle: BegLoop self)
				(soundFx2 number: 346 setLoop: 1 play:)
			)
			(11
				(if modelessDialog (modelessDialog dispose:))
				(messager say: 1 0 30 1 self 340)
			)
			(12
				(nightMare setCycle: Forward)
				(= seconds 3)
			)
			(13
				(messager say: 1 0 30 2 self 340)
			)
			(14
				(ego posn: egoX egoY reset: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance catchNiteMare of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 11 340)
				(theGame handsOff:)
				(theIconBar disable: 6)
				(ego setMotion: PolyPath 193 131 self)
			)
			(1
				(ego setHeading: 45)
				(= cycles 6)
			)
			(2
				(ego
					view: 336
					normal: 0
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					posn: (+ (ego x?) 14) (+ (ego y?) 7)
					setCycle: Forward
				)
				(= seconds 5)
			)
			(3
				(messager say: 1 0 4 1 self 340)
			)
			(4
				(KQ6Print
					say: 0 1 0 4 2
					posn: 10 10
					ticks: 120
					modeless: 1
					init:
				)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego setLoop: 1 cel: 0 cycleSpeed: 8 setCycle: Forward)
				(= seconds 12)
			)
			(6 (ego setCycle: EndLoop self))
			(7 (ego cel: 0) (= cycles 4))
			(8
				(ego setLoop: 7 cel: 0)
				(= cycles 6)
			)
			(9 (ego cel: 1) (= cycles 6))
			(10
				(ego setLoop: 8)
				(= seconds 1)
			)
			(11
				(smoke init: setCycle: EndLoop self)
			)
			(12
				(soundFx2 number: 346 setLoop: 1 play:)
				(nightMare setCycle: BegLoop)
				(smoke setLoop: 10 cel: 0 setCycle: Forward)
				(= seconds 5)
			)
			(13
				(if modelessDialog (modelessDialog dispose:))
				(messager say: 1 0 4 3 self 340)
			)
			(14
				(ego view: 336 setLoop: 2 setCycle: EndLoop self)
				(smoke setLoop: 9 cel: 10 setCycle: BegLoop)
				(nightMare
					view: 337
					loop: 0
					cel: 0
					posn: 265 112
					setCycle: EndLoop self
				)
			)
			(15 (smoke dispose:))
			(16
				(messager say: 1 0 4 4 self 340)
			)
			(17
				(theSkull init: setPri: 1 stopUpd:)
				(ego put: 11 340)
				(theIconBar curIcon: (theIconBar at: 0))
				(ego
					posn: (- (ego x?) 14) (- (ego y?) 7)
					ignoreActors:
					reset: 6
					setPri: 9
				)
				(nightMare
					setScale: Scaler 100 80 134 112
					posn: (- (nightMare x?) 5) (- (nightMare y?) 3)
					setLoop: 1
					setPri: 10
					setCycle: Walk
					illegalBits: 0
					setMotion: MoveTo 220 134 self
				)
			)
			(18
				(messager say: 1 0 4 5 self 340)
			)
			(19
				(nightMare
					setScale: 0
					view: 336
					setLoop: 5
					cel: 0
					posn: 216 135
					setCycle: EndLoop
				)
				(ego setMotion: PolyPath 167 122 self)
			)
			(20
				(ego setHeading: 135)
				(= cycles 8)
			)
			(21
				(nightMare hide:)
				(ego
					view: 336
					normal: 0
					posn: 216 135
					setLoop: 3
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(22
				(nightMare
					show:
					view: 335
					setLoop: 1
					cel: 0
					posn: 216 135
				)
				(ego
					setScale: 0
					view: 336
					normal: 0
					setLoop: 6
					cel: 0
					setPri: (+ (nightMare priority?) 1)
					posn: (+ (nightMare x?) 35) (- (nightMare y?) 46)
				)
				(theGame givePoints: 2)
				(= cycles 2)
			)
			(23
				(messager say: 1 0 4 6 self 340)
			)
			(24
				(theGlobalSound number: 155 setLoop: -1 play:)
				(theMusic fade:)
				(soundFx fade:)
				(soundFx2 fade:)
				(= ticks 4)
			)
			(25
				(++ local0)
				(nightMare cel: (+ (nightMare cel?) 1))
				(ego cel: (+ (ego cel?) 1))
				(= ticks 14)
			)
			(26
				(if (< local0 9) (= state (- state 2)))
				(self cue:)
			)
			(27
				(ego x: 1000)
				(nightMare cel: (+ (nightMare cel?) 1))
				(= seconds 2)
			)
			(28
				(theGame handsOn:)
				(theIconBar enable: 6)
				(curRoom newRoom: 155)
			)
		)
	)
)

(instance birdSound of Sound
	(properties)
)
