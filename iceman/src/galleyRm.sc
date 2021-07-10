;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include sci.sh)
(use Main)
(use Intrface)
(use n316)
(use InitAllFeatures)
(use GoToSaid)
(use ForCount)
(use Grooper)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	galleyRm 0
)

(instance galleyRm of Rm
	(properties
		picture 32
		horizon 12
		north 25
		south 34
		west 41
		vanishingX 315
		vanishingY 58
	)
	
	(method (init)
		(super init:)
		(ego init: setPri: -1)
		(if (IsInvItemInRoom curRoomNum 4)
			(rumBottle init: setPri: 8 ignoreActors: 1)
		)
		(switch prevRoomNum
			(25
				(ego
					ignoreControl: -32768
					posn: 93 21
					view: 32
					setLoop: 3
					setScript: decendStairsScript
				)
			)
			(39
				(egoChair hide:)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 32
					setLoop: 7
					ignoreControl: -32768
					posn: (egoChair x?) (egoChair y?)
					setCel: 16
					cycleSpeed: 2
					setPri: -1
					setScript: egoStandsScript
				)
				(oldSalt
					init:
					ignoreControl: -32768
					posn: (oldSaltChair x?) (oldSaltChair y?)
					view: 32
					cycleSpeed: 2
					setLoop: 6
					setCel: 16
					setScript: oldSaltStandScript
				)
				(rumBottle dispose:)
			)
			(west (ego posn: 59 108))
			(south
				(ego posn: 109 141 heading: 45 loop: 6)
			)
			(else 
				(HandsOn)
				(ego posn: 116 122 view: 232)
			)
		)
		(oldSaltChair init: ignoreActors: 1)
		(egoChair init:)
		(chef init: stopUpd: setScript: chefScript)
		(addToPics add: pipes skillet doit:)
		(InitAllFeatures)
		(self setFeatures: stairsFeat setRegions: 314)
		(RemoveInvItems curRoomNum 4)
	)
	
	(method (doit)
		(if (== (ego onControl: 1) 16384)
			(self newRoom: 34)
		else
			(super doit:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'stand')
				(if (== (ego view?) 32)
					(ego setScript: egoStandsScript)
				else
					(Print 32 0)
				)
			)
			((Said 'look>')
				(cond 
					((Said '[<at,around][/room]') (Print 32 1) (Print 32 2) (Print 32 3))
					((Said '/table')
						(if
							(and
								(cast contains: rumBottle)
								(not (& (rumBottle signal?) $0008))
							)
							(Print 32 4)
						else
							(Print 32 5)
						)
					)
					((Said '/kitchen') (Print 32 6))
					((Said '/menu') (Print 32 7))
				)
			)
		)
	)
)

(instance pipes of PV
	(properties
		y 185
		x 193
		view 32
		priority 15
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/pipe]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 32 8))
				)
			)
		)
	)
)

(instance skillet of PV
	(properties
		y 45
		x 164
		view 32
		cel 1
		priority 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/pan,pan'))
			((Said 'look[<at][/pan,pan]') (Print 32 9))
		)
	)
)

(instance chef of Prop
	(properties
		y 78
		x 142
		view 32
		loop 1
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/chef,man,cook]>')
				(cond 
					((TurnIfSaid self event 'address,(look[<at])/*'))
					((Said 'look[<at]') (Print 32 10))
					((Said 'address') (Print 32 11))
					((Said 'ask//key') (Print 32 12))
				)
			)
		)
	)
)

(instance serveItScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 32 13 #at 30 20 #time 8 #dispose)
				(chef setLoop: 5 cycleSpeed: 2 setCycle: End self)
			)
			(1
				(burger init:)
				(chef
					setLoop: 1
					setCel: 0
					cycleSpeed: 0
					setScript: chefScript
				)
			)
		)
	)
)

(instance burger of View
	(properties
		view 32
		cel 5
	)
	
	(method (init)
		(super init:)
		(self posn: (+ (chef x?) 34) (- (chef y?) 1))
	)
)

(instance chefScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds (Random 5 20)))
			(1
				(client
					setLoop: 4
					setCycle: ForwardCounter (Random 5 8) self
				)
			)
			(2
				(client setLoop: 1 setCycle: End self)
			)
			(3
				(client stopUpd:)
				(self init:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'get,order/food,sandwich,burger')
					(and (not register) (Said 'eat'))
				)
				(cond 
					((!= (ego view?) 232) (NotNow))
					(register (Print 32 14))
					(else (client setScript: serveItScript) (= register 1))
				)
			)
			((Said 'eat[/food,sandwich,burger]')
				(cond 
					((!= (ego view?) 232) (NotNow))
					((cast contains: burger) (Print 32 15) (burger dispose:))
					(else (Print 32 16))
				)
			)
		)
	)
)

(instance egoStandsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					heading: 90
					yStep: 2
					setMotion: MoveTo (egoChair x?) (egoChair y?)
					setCycle: Beg self
				)
			)
			(1
				(egoChair show:)
				(ego
					posn: (- (egoChair x?) 23) (+ (egoChair y?) 1)
					cycleSpeed: 0
					heading: 270
					view: 232
					setLoop: -1
					setLoop: Grooper
					setCycle: Walk
					setMotion: MoveTo 229 106 self
				)
			)
			(2
				(ego heading: 270)
				((ego looper?) doit: ego (ego heading?) self)
			)
			(3
				(HandsOn)
				(ego observeControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance rumBottle of View
	(properties
		y 98
		x 239
		view 32
		cel 2
		priority 9
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((& signal $0008))
			((Said '[/bottle[<rum]]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 32 17))
					((Said 'get,drink')
						(cond 
							((>= (ego distanceTo: self) 40) (NotClose))
							((cast contains: oldSalt) (event claimed: 0))
							(else (oldSalt init: setScript: oldSaltScript))
						)
					)
				)
			)
		)
	)
)

(instance oldSalt of Act
	(properties
		y 112
		x 46
		view 132
	)
	
	(method (init)
		(super init:)
		(self ignoreControl: -32768 setCycle: Walk)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/man[<old]'))
			((Said 'look[<at][/man[<old]]') (Print 32 18))
			((Said 'ask[/name]') (Print 32 19) (Print 32 20) (Print 32 21))
		)
	)
)

(instance oldSaltScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 32 22 #at 30 20 #dispose)
				(oldSalt
					ignoreActors: 1
					setMotion: MoveTo (- (oldSaltChair x?) 13) (oldSaltChair y?) self
				)
				(if (InRect 210 115 257 124 ego)
					(ego setMotion: MoveTo 209 120)
				)
			)
			(1
				(if (and (== (ego x?) 209) (== (ego y?) 120))
					(ego loop: 0)
				)
				(if modelessDialog (modelessDialog dispose:))
				(oldSaltChair hide:)
				(oldSalt
					x: (+ (oldSalt x?) 13)
					view: 32
					cycleSpeed: 2
					setLoop: 6
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(Print 32 23 #at 30 20)
				(= cycles 2)
			)
			(3
				(Print 32 24 #at 30 20)
				(User canInput: 1)
				(= seconds 10)
			)
			(4
				(Print 32 25 #at 30 20)
				((inventory at: 4) moveTo: -1)
				(rumBottle hide:)
				(client setScript: oldSaltStandScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((!= state 3))
			((or (Said 'affirmative') (Said 'play[/game]')) (ego setScript: diceGameScript) (self dispose:))
			((Said 'n') (self cue:))
		)
	)
)

(instance oldSaltStandScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oldSalt ignoreActors: 1 heading: 90 setCycle: Beg self)
			)
			(1
				(oldSaltChair show:)
				(oldSalt
					view: 132
					setCycle: Walk
					setLoop: -1
					setLoop: Grooper
					heading: 270
				)
				((oldSalt looper?) doit: oldSalt (oldSalt heading?))
				(= cycles 10)
			)
			(2
				(oldSalt
					cycleSpeed: 0
					setPri: -1
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 46 112 self
				)
			)
			(3
				(if (not (ego mover?)) (HandsOn))
				(if (!= (ego view?) 232) (User canControl: 0))
				(self dispose:)
			)
		)
	)
)

(instance oldSaltChair of View
	(properties
		y 121
		x 235
		view 32
		cel 3
	)
)

(instance diceGameScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= (ego view?) 32)
					(self setScript: egoSitScript self)
				else
					(= cycles 1)
				)
			)
			(1 (curRoom newRoom: 39))
		)
	)
)

(instance egoSitScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (== (ego onControl: 1) 8192)
					(ego setMotion: MoveTo 179 120 self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(if
					(or
						(= temp0 (< (ego x?) (- (egoChair x?) 32)))
						(= temp1 (< (ego y?) (- (egoChair y?) 8)))
					)
					(ego
						setAvoider: Avoid
						observeControl: 8192
						setMotion: MoveTo (- (egoChair x?) 32) (- (egoChair y?) 8) self
					)
				else
					(= cycles 1)
				)
			)
			(2
				(ego
					ignoreControl: -24576
					setAvoider: 0
					setMotion: MoveTo (- (egoChair x?) 23) (+ (egoChair y?) 1) self
				)
			)
			(3
				(egoChair hide:)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 32
					setLoop: 7
					posn: (egoChair x?) (egoChair y?)
					setCel: 0
					cycleSpeed: 1
					yStep: 1
					setCycle: CT 7 1 self
				)
			)
			(4
				(ego
					setMotion: MoveTo (egoChair x?) (+ (egoChair y?) 4)
					setCycle: End self
				)
			)
			(5
				(HandsOn)
				(User canControl: 0)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit') (Print 32 0))
		)
	)
)

(instance egoChair of View
	(properties
		y 107
		x 256
		view 32
		cel 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit')
				(if (== (ego view?) 32)
					(Print 32 0)
				else
					(ego setScript: egoSitScript)
				)
			)
		)
	)
)

(instance stairsFeat of Feature
	(properties
		y 126
		x 66
		heading 180
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/stair,ladder]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 32 26))
					((GoToIfSaid self event 62 130 'climb' 32 27))
					((Said 'climb') (ego setScript: climbStairsScript))
				)
			)
		)
	)
)

(instance climbStairsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 62 126 self)
			)
			(1
				(ego heading: 0)
				((ego looper?) doit: ego)
				(= cycles 4)
			)
			(2
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 32
					setLoop: 2
					setPri: 9
					cycleSpeed: 1
					illegalBits: 0
					setCel: 0
				)
				(= cycles 2)
			)
			(3
				(= start state)
				(ego posn: (+ (ego x?) 2) (- (ego y?) 7) setCel: 1)
				(= cycles 2)
			)
			(4 (ego setCycle: CT 3 1 self))
			(5
				(ego posn: (+ (ego x?) 2) (- (ego y?) 7) setCel: 4)
				(= cycles 2)
			)
			(6 (ego setCycle: CT 0 1 self))
			(7 (self init:))
		)
	)
)

(instance decendStairsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 32
					setLoop: 3
					setPri: 9
					cycleSpeed: 1
					illegalBits: 0
					setCel: 0
				)
				(= cycles 2)
			)
			(1
				(= start state)
				(ego posn: (- (ego x?) 2) (- (ego y?) -7) setCel: 1)
				(= cycles 2)
			)
			(2 (ego setCycle: CT 3 1 self))
			(3
				(ego posn: (- (ego x?) 2) (- (ego y?) -7) setCel: 4)
				(= cycles 2)
			)
			(4 (ego setCycle: CT 0 1 self))
			(5
				(if (< (ego y?) 125)
					(self init:)
				else
					(ego
						view: 232
						heading: 180
						loop: 2
						setCycle: Walk
						setLoop: -1
						setLoop: Grooper
						cycleSpeed: 0
						setPri: -1
						observeControl: -32768
					)
					(HandsOn)
				)
			)
		)
	)
)
