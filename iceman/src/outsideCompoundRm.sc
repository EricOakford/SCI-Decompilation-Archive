;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use EgoDead)
(use Approach)
(use GoToSaid)
(use LoadMany)
(use Grooper)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	outsideCompoundRm 0
)

(local
	gunInPlatter
	local1
	local2
	local3
)
(instance outsideCompoundRm of Room
	(properties
		picture 81
		north 86
		east 82
	)
	
	(method (init)
		(super init:)
		(self
			setRegions: 310
			setFeatures:
				windo
				((Clone windo)
					x: 266
					y: 26
					z: 0
					nsLeft: 247
					nsTop: 12
					nsRight: 285
					nsBottom: 40
					yourself:
				)
				doorFeat
				building
		)
		(Load VIEW 787)
		(Load VIEW 285)
		(switch prevRoomNum
			(east
				(ego posn: 314 (ego y?) loop: 1 init:)
				(guardRight
					view: 787
					setLoop: GradualLooper
					loop: 2
					cel: 0
					posn: 223 78
					init:
				)
				(self setScript: offLimitsScript)
			)
			(80
				(LoadMany VIEW 85 684 185 687 287)
				(guardRight
					view: 787
					setLoop: GradualLooper
					loop: 2
					cel: 0
					posn: 223 78
					init:
				)
				(van init:)
				(vanDoor view: 185 loop: 2 cel: 0 init:)
				(self setScript: vanInScript)
			)
			(else 
				(LoadMany VIEW 181 185 281 376 684 287)
				(LoadMany SOUND 82 18 218)
				(stacy view: 181 loop: 0 cel: 0 posn: 262 90)
				(van view: 185 loop: 0 cel: 0 posn: 256 97 init:)
				(vanDoor
					view: 185
					loop: 3
					cel: 0
					x: (- (van x?) 2)
					y: (+ (van y?) 1)
					z: 11
					init:
				)
				(self setScript: shootOutScript)
			)
		)
		(addToPics add: arch doit:)
		(door init: ignoreActors: TRUE)
		(guardLeft
			view: 285
			loop: 1
			cel: 0
			illegalBits: 0
			ignoreActors: TRUE
			posn: 154 78
			init:
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,town,scene]')
				(Print 81 0)
			)
			((Said 'load/gun')
				(Print 81 1)
			)
		)
	)
	
	(method (newRoom nRoom)
		(cond 
			((== (ego script?) stayHereScript) (return))
			(
				(and
					(not (OneOf nRoom 86 88))
					(!= prevRoomNum 82)
				)
				(ego setScript: stayHereScript)
			)
			(else
				(super newRoom: nRoom &rest)
			)
		)
	)
)

(instance stayHereScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 81 2)
				(ego setMotion: MoveTo (- (ego x?) 20) (ego y?) self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance offLimitsScript of Script

	(method (doit)
		(super doit:)
		(if (and (== (ego onControl: origin) cBLUE) (== state 0))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Print 81 3)
				(guardRight
					setAvoider: Avoider
					setCycle: Walk
					setMotion: Approach ego 20 self
				)
			)
			(2
				(guardRight
					heading: (GetAngle
						(guardRight x?)
						(guardRight y?)
						(ego x?)
						(ego y?)
					)
				)
				((guardRight looper?)
					doit: guardRight (guardRight heading?)
				)
				(= cycles 5)
			)
			(3
				(self setScript: arabicScript)
			)
		)
	)
)

(instance vanInScript of Script

	(method (doit)
		(super doit:)
		(-- local2)
		(cond 
			((!= state 4))
			((== local2 1)
				(= local2 0)
				(client setScript: orderedOutScript)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(van setLoop: 0 setStep: 8 8 setMotion: MoveTo 256 97)
				(= cycles 8)
			)
			(1
				(van setStep: 6 6)
				(= cycles 5)
			)
			(2
				(van setStep: 4 4)
				(= cycles 8)
			)
			(3
				(van setStep: 2 2 setMotion: MoveTo 256 97 self)
			)
			(4
				(van stopUpd:)
				(User canInput: TRUE)
				(= local2 200)
			)
			(5
				(Print 81 4)
				(vanDoor setCycle: EndLoop self)
			)
			(6
				(vanDoor setLoop: 3 stopUpd:)
				(ego
					view: (if (ego has: 13) 85 else 684)
					posn: (- (van x?) 10) (+ (van y?) 3)
					loop: 2
					setLoop: GradualLooper
					setCycle: Walk
					init:
				)
				(HandsOn)
				(= local3 1)
				(= local1 200)
				(client setScript: outOfVanScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'exit,exit[/truck]')
					(Said 'open/truck,door[<truck]')
					(Said 'get[<out][/truck]')
					(Said 'stand')
				)
				(self cue:)
			)
			((Said 'open/tray,food')
				(DontNeedTo)
			)
			((Said 'get,(pick<up)/food,platter,tray')
				(cond 
					((ego has: iFoodPlatter)
						(Print 81 5)
					)
					((== gunInPlatter TRUE)
						(Print 81 6)
						(ego get: iFoodPlatter)
					)
					(else
						(Print 81 7)
						(ego get: iFoodPlatter)
					)
				)
			)
			(
				(or
					(Said 'conceal/gun')
					(Said 'adjust,conceal<in/gun/food,platter')
				)
				(if (== gunInPlatter FALSE)
					(Print 81 8)
					(= gunInPlatter TRUE)
					(theGame changeScore: 2)
				else
					(Print 81 9)
				)
			)
			((Said 'look[<at]/compound')
				(Print 81 10)
			)
			((Said 'address/guard')
				(Print 81 11)
			)
			((Said 'shoot,fire[/guard,gun]')
				(Print 81 12)
			)
		)
	)
)

(instance orderedOutScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guardRight
					setAvoider: Avoider
					setCycle: Walk
					setMotion: MoveTo 177 84 self
				)
			)
			(1
				(guardRight setMotion: MoveTo 177 100 self)
			)
			(2
				(guardRight setMotion: MoveTo 185 100 self)
			)
			(3
				(Print 81 13)
				(Print 81 14)
				(vanDoor setCycle: EndLoop self)
			)
			(4
				(vanDoor setLoop: 3 stopUpd:)
				(ego
					view: (if (ego has: iFoodPlatter) 85 else 684)
					posn: (- (van x?) 10) (+ (van y?) 3)
					loop: 2
					setLoop: -1
					setLoop: GradualLooper
					setCycle: Walk
					init:
				)
				(= cycles 1)
			)
			(5
				(ego heading: 270)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 3)
			)
			(6
				(self setScript: arabicScript)
			)
		)
	)
)

(instance outOfVanScript of Script

	(method (doit)
		(super doit:)
		(-- local1)
		(cond 
			((!= state 0))
			((and (== (ego onControl: origin) cCYAN) (ego has: iFoodPlatter))
				(client setScript: friskScript)
			)
			((or (== (ego onControl: origin) cGREEN) (== local1 1))
				(= local1 0)
				(self cue:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Print 81 15)
				(guardRight
					setCycle: Walk
					setAvoider: Avoider
					setMotion: Approach ego 20 self
				)
			)
			(2
				(guardRight
					heading: (GetAngle
						(guardRight x?)
						(guardRight y?)
						(ego x?)
						(ego y?)
					)
				)
				((guardRight looper?)
					doit: guardRight (guardRight heading?)
				)
				(ego
					heading: (GetAngle
						(ego x?)
						(ego y?)
						(guardRight x?)
						(guardRight y?)
					)
				)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 5)
			)
			(3
				(self setScript: arabicScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'shoot,fire[/guard,gun]')
				(client setScript: egoShootScript)
			)
			((Said 'open/tray,food')
				(DontNeedTo)
			)
			((Said 'get,(pick<up)/food,platter,tray')
				(if (ego has: iFoodPlatter)
					(AlreadyTook)
				else
					(client setScript: goGetFoodScript)
				)
			)
			(
				(or
					(Said 'conceal/gun')
					(Said 'adjust,conceal<in/gun/platter,food')
				)
				(if (== gunInPlatter FALSE)
					(client setScript: goHideGunScript)
				else
					(Print 81 9)
				)
			)
			((Said 'close/door,truck[<truck]')
				(DontNeedTo)
			)
		)
	)
)

(instance friskScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: MoveTo 175 78 self
				)
			)
			(1
				(if (not (tunisia madeCall?))
					(Print 81 16)
				else
					(Print 81 17)
				)
				(Print 81 18)
				(= cycles 1)
			)
			(2
				(guardRight
					ignoreActors: TRUE
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo (ego x?) (+ (ego y?) 7) self
				)
			)
			(3
				(guardRight view: 485 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(guardRight setCycle: BegLoop self)
			)
			(5
				(cond 
					((== gunInPlatter FALSE)
						(Print 81 19)
						(Print 81 20)
					)
					((not (tunisia madeCall?))
						(Print 81 21)
						(Print 81 22)
						(Print 81 19)
						(Print 81 20)
					)
					(else
						(Print 81 23)
					)
				)
				(= cycles 1)
			)
			(6 (door setCycle: EndLoop self))
			(7
				(ego setMotion: MoveTo 175 68 self)
				(guardRight
					ignoreActors: TRUE
					illegalBits: 0
					view: 787
					setCycle: Walk
					setMotion: MoveTo 175 68 self
				)
			)
			(8
				(ego dispose:)
			)
			(9
				(guardRight dispose:)
				(door setCycle: BegLoop self)
			)
			(10
				(if (or (== gunInPlatter 0) (not (tunisia madeCall?)))
					(EgoDead 970 0 0 81 24)
				else
					(curRoom newRoom: 86)
				)
			)
		)
	)
)

(instance goGetFoodScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego y?) 98)
					(ego setAvoider: Avoider setMotion: MoveTo 194 104 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					setMotion: MoveTo (- (van x?) 10) (+ (van y?) 3) self
				)
			)
			(2
				(ego heading: 0)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 4)
			)
			(3
				(Print 81 25)
				(ego setAvoider: 0 view: 85 get: 13)
				(= cycles 5)
			)
			(4
				(ego heading: 180)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 6)
			)
			(5
				(HandsOn)
				(= local1 200)
				(client setScript: outOfVanScript)
			)
		)
	)
)

(instance goHideGunScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego y?) 98)
					(ego setAvoider: Avoider setMotion: MoveTo 194 104 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					setMotion: MoveTo (- (van x?) 10) (+ (van y?) 3) self
				)
			)
			(2
				(ego heading: 0)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 4)
			)
			(3
				(Print 81 26)
				(= gunInPlatter 1)
				(theGame changeScore: 2)
				(= cycles 5)
			)
			(4
				(ego setAvoider: 0 heading: 180)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 6)
			)
			(5
				(HandsOn)
				(= local1 200)
				(client setScript: outOfVanScript)
			)
		)
	)
)

(instance egoShootScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider)
				(if (ego inRect: 270 130 319 189)
					(ego setMotion: MoveTo 279 123 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: MoveTo 200 100 self)
				(Print 81 27)
			)
			(2
				(ego view: 687 loop: 0 cel: 0 setCycle: EndLoop self)
				(Print 81 28)
			)
			(3
				(guardLeft view: 287 loop: 1 setCycle: EndLoop)
				(ego loop: 1)
				(= cycles 7)
			)
			(4
				(ego setCycle: BegLoop)
				(guardRight view: 287 loop: 0 setCycle: EndLoop self)
			)
			(5
				(Print 81 29)
				(Print 81 30)
				(guardNew
					view: 787
					loop: 2
					cel: 0
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: Walk
					posn: 176 70
					init:
				)
				(door setCycle: EndLoop self)
			)
			(6
				(ego loop: 0 setCycle: EndLoop)
				(guardNew
					setMotion: MoveTo (guardNew x?) (+ (guardNew y?) 5) self
				)
			)
			(7
				(Print 81 31)
				(Print 81 32)
				(guardNew view: 187 loop: 0 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(8
				(ego
					view: 687
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(guardNew setCycle: EndLoop)
			)
			(9 (= cycles 10))
			(10 (EgoDead 970 1 0 81 33))
		)
	)
)

(instance shootOutScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 684
					loop: 2
					setCycle: Walk
					illegalBits: 0
					ignoreActors: TRUE
					posn: 175 58
					setPri: -1
					init:
				)
				(ambass
					view: 887
					loop: 2
					setCycle: Walk
					illegalBits: 0
					ignoreActors: TRUE
					posn: 180 65
					init:
				)
				(door setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 175 68)
				(ambass setMotion: MoveTo 185 75 self)
			)
			(2
				(guardLeft loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(Print 81 34)
				(ego view: 281 loop: 1 setCycle: EndLoop)
				(ambass view: 281 loop: 0 setCycle: EndLoop)
				(= cycles 10)
			)
			(4
				(Print 81 35)
				(stacy
					init:
					setStep: 8 2
					setCel: 0
					setMotion: MoveTo 205 90 self
				)
			)
			(5
				(shot number: (SoundFX 18) play:)
				(stacy setCycle: EndLoop self)
			)
			(6
				(guardLeft loop: 2 cel: 0)
				(stacy view: 250 loop: 3 setLoop: -1 setStep: 3 2)
				(= cycles 1)
			)
			(7
				(globalSound number: 82 play:)
				(guardLeft posn: 149 77 cel: 1)
				(splat
					view: 285
					loop: 3
					cel: 0
					x: 146
					y: 78
					z: 34
					init:
					cycleSpeed: 2
					setCycle: EndLoop
				)
				(= cycles 1)
			)
			(8
				(ego setCycle: BegLoop)
				(ambass setCycle: BegLoop)
				(guardLeft cycleSpeed: 1 setCycle: EndLoop self)
			)
			(9
				(Print 81 36)
				(Print 81 37)
				(stacy setMotion: MoveTo (van x?) (- (van y?) 10))
				(ambass
					view: 887
					loop: 2
					setCycle: Walk
					setMotion: MoveTo (van x?) (- (van y?) 5)
				)
				(ego
					view: 684
					loop: 2
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo 176 86 self
				)
			)
			(10
				(ego setMotion: MoveTo 200 98 self)
			)
			(11
				(ego
					setMotion: MoveTo (- (van x?) 10) (+ (van y?) 3) self
				)
			)
			(12
				(ego heading: 0)
				((ego looper?) doit: ego (ego heading?) self)
			)
			(13
				(ego dispose:)
				(ambass dispose:)
				(stacy dispose:)
				(vanDoor loop: 4 setCycle: EndLoop self)
			)
			(14
				(van
					illegalBits: 0
					ignoreActors: TRUE
					setLoop: 0
					setCel: 0
					setStep: 2 2
					setMotion: MoveTo -100 (van y?)
				)
				(= cycles 8)
			)
			(15
				(van setStep: 4 4)
				(= cycles 8)
			)
			(16
				(van setStep: 6 6)
				(= cycles 8)
			)
			(17
				(van setStep: 8 8)
				(= cycles 8)
			)
			(18
				(van setStep: 10 10 setMotion: MoveTo -100 (van y?) self)
			)
			(19
				(inventory eachElementDo: #owner 0)
				(curRoom newRoom: 88)
			)
		)
	)
)

(instance arabicScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(InitArab guardRight)
				(= seconds 2)
			)
			(1
				(DisposeArab)
				(= cycles 2)
			)
			(2
				(InitArab ego)
				(= seconds 2)
			)
			(3
				(DisposeArab)
				(= cycles 2)
			)
			(4
				(InitArab guardRight)
				(= seconds 2)
			)
			(5
				(DisposeArab)
				(= cycles 2)
			)
			(6
				(if (== client offLimitsScript)
					(Print 81 38)
				else
					(Print 81 39)
				)
				(EgoDead 970 0 0 81 40)
			)
		)
	)
)

(instance guardLeft of Actor
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/guard,man]>')
				(cond 
					((Said 'address')
						(Print 81 41)
					)
					((Said 'look[<at]')
						(Print 81 42)
					)
				)
			)
		)
	)
)

(instance guardRight of Actor
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/guard,man]>')
				(cond 
					((Said 'address')
						(Print 81 41)
					)
					((Said 'look[<at]')
						(Print 81 42)
					)
				)
			)
		)
	)
)

(instance guardNew of Actor)

(instance van of Actor
	(properties
		y 97
		x 390
		view 185
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/truck]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 81 43)
					)
					((Said 'enter,(look<in)')
						(if (== local3 0)
							(Print 81 44)
						else
							(Print 81 45)
						)
					)
				)
			)
		)
	)
)

(instance stacy of Actor)

(instance ambass of Actor)

(instance splat of Prop)

(instance door of Prop
	(properties
		y 71
		x 155
		view 81
	)
)

(instance vanDoor of Prop
	
	(method (doit)
		(self x: (- (van x?) 2) y: (+ (van y?) 1) z: 11)
		(super doit:)
	)
)

(instance arch of PicView
	(properties
		y 71
		x 187
		view 81
		loop 1
		signal ignrAct
	)
)

(instance shot of Sound
	(properties
		number 18
		priority 2
	)
)

(instance building of RFeature
	(properties
		y 35
		x 159
		nsBottom 71
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 81 46)
					)
				)
			)
		)
	)
)

(instance windo of RFeature
	(properties
		y 23
		x 89
		nsTop 14
		nsLeft 79
		nsBottom 32
		nsRight 99
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 81 47)
					)
					((Said 'open,(look,climb<(in,through))')
						(BadIdea)
					)
				)
			)
		)
	)
)

(instance doorFeat of RFeature
	(properties
		y 37
		x 186
		nsTop 3
		nsLeft 154
		nsBottom 71
		nsRight 219
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 81 48)
					)
					((Said 'knock')
						(BadIdea)
					)
				)
			)
		)
	)
)
