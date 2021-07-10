;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include sci.sh)
(use Main)
(use Intrface)
(use tunisia)
(use EgoDead)
(use InitAllFeatures)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use DPath)
(use Follow)
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
	inCompoundRm 0
)

(local
	theWestGuard
	local1
)
(instance inCompoundRm of Rm
	(properties
		picture 87
		vanishingX 117
		vanishingY 21
	)
	
	(method (init)
		(super init:)
		(HandsOn)
		(User canControl: 0)
		(LoadMany 128 85 684 187 287 387 487 687 787 887 987)
		(LoadMany 132 23 223 27 227 81 90)
		(gunShot number: (SoundFX 27))
		(machineGun number: (SoundFX 23))
		(ego
			view: 85
			posn: 126 179
			loop: 3
			setLoop: -1
			ignoreActors: 0
			cycleSpeed: 0
			init:
			setCycle: Walk
		)
		(addToPics
			add: table mosaicPic
			eachElementDo: #init
			doit:
		)
		(self setScript: messageScript)
		(InitAllFeatures)
		(backGuard init:)
		(westGuard
			init:
			setCycle: Walk
			setLoop: Grooper
			setScript: westGuardScript
		)
		(ambassador init: setScript: ambassScript)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'load/gun')
				(cond 
					((not (ego has: 8)) (event claimed: 0))
					((== (backGuard view?) 287) (Print 87 0))
					(else (Print 87 1))
				)
			)
			((or (Said 'exit[/room]') (Said 'go<out')) (Print 87 2))
			((Said 'throw/food') (Print 87 3))
			((Said 'kill/man,guard') (Print 87 4))
			((Said 'shoot,fire[/guard,gun]')
				(cond 
					((ego script?) (event claimed: 0))
					((== (backGuard view?) 287) (Print 87 0))
					(else (Print 87 5))
				)
			)
			((Said 'look<out/shutter') (Print 87 6))
			((Said 'get/gun<guard') (Print 87 7))
		)
	)
)

(instance messageScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(Print 87 8 #at 10 10)
				(Print 87 9 #at 10 10)
				(self dispose:)
			)
		)
	)
)

(instance ambassScript of Script
	(properties)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/ambassador,man>')
				(cond 
					((Said 'address')
						(if (== (backGuard view?) 487)
							(Print 87 10)
						else
							(Print 87 11)
						)
					)
					((Said 'clear,untie')
						(if (== (backGuard view?) 487)
							(Print 87 12)
						else
							(client setScript: freeAmbassadorScript)
						)
					)
					((Said 'look') (Print 87 13))
				)
			)
		)
	)
)

(instance westGuardScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 97 136 self)
			)
			(1
				(client heading: 180)
				((client looper?) doit: client (client heading?))
				(= seconds 15)
			)
			(2
				(if (not (ego script?)) (= cycles 2))
			)
			(3
				(HandsOff)
				(Print 87 14 #at 10 10)
				(if (not (ego script?))
					(self setScript: forcedOutScript self)
				else
					(= cycles 2)
				)
			)
			(4
				(ego setAvoider: Avoid setMotion: MoveTo 126 179 self)
			)
			(5
				(westGuard illegalBits: 0 setMotion: MoveTo 106 178 self)
				(ego illegalBits: 0 setMotion: MoveTo 126 225)
			)
			(6 (EgoDead 918 0 0 87 15))
		)
	)
)

(instance walkToEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Print 87 14 #at 10 10)
				(westGuard setMotion: MoveTo 155 139)
				(User canInput: 1)
			)
		)
	)
)

(instance forcedOutScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: -1
					setAvoider: Avoid
					setMotion: MoveTo (- (table x?) 40) (- (table y?) 3) self
				)
			)
			(1
				(Print 87 16 #at 10 10)
				(ego
					view: 187
					loop: 1
					ignoreControl: -32768
					heading: 90
					ignoreActors:
					setCycle: End self
				)
			)
			(2
				(foodView init:)
				(ego
					view: 684
					loop: 0
					setLoop: -1
					setCycle: Walk
					observeControl: -32768
					setAvoider: 0
					put: 13
				)
				(self dispose:)
			)
		)
	)
)

(instance setDownFoodScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((< state 2))
			(register (-- register))
			((westGuardScript client?) (westGuardScript cue:) (= register 30000))
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setLoop: -1
					setAvoider: Avoid
					setMotion: MoveTo (- (table x?) 40) (- (table y?) 3) self
				)
			)
			(1
				(if (not demoScripts) (Print 87 16 #at 10 10))
				(ego
					view: 187
					loop: 1
					ignoreControl: -32768
					heading: 90
					ignoreActors:
					setCycle: End self
				)
			)
			(2
				(if demoScripts (self dispose:))
				(User canInput: 1)
				(foodView init:)
				(ego
					view: 684
					loop: 0
					setLoop: -1
					setCycle: Walk
					illegalBits: -32768
					setAvoider: 0
					ignoreActors: 0
				)
				(= register 100)
			)
			(3
				(HandsOff)
				(foodView setCel: 3)
				(ego
					view: 187
					loop: 2
					cel: 0
					heading: 90
					setAvoider: 0
					illegalBits: 0
					ignoreActors: 1
					setCycle: End self
				)
			)
			(4
				(foodLidView init:)
				(HandsOn)
				(ego illegalBits: -32768 ignoreActors: 0)
				(User canControl: 0)
				(westGuard setScript: walkToEgoScript)
			)
			(5
				(Print 87 17 #at 10 10)
				(= local1 1)
				(ego view: 687 cel: 0 loop: 0 setCycle: End)
				(= theWestGuard westGuard)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(== (event type?) evKEYBOARD)
						(== (event message?) KEY_F10)
					)
					(Said 'shoot,fire[/guard,gun,man]')
				)
				(cond 
					((== theWestGuard westGuard)
						(if
						(and (== (westGuard x?) 155) (== (westGuard y?) 139))
							(westGuard setScript: westShootEgoScript)
						else
							(westGuard setScript: westGetsShotScript)
							(gunShot play:)
							(globalSound number: 81 play:)
						)
					)
					((== theWestGuard backGuard)
						(backGuard setScript: backGetsShotScript)
						(event claimed: 1)
					)
					(else (Print 87 18))
				)
				(event claimed: 1)
			)
			((Said '[/lid,cover]>')
				(if
					(and
						(or
							(Said 'move,open,detach,(get<off)')
							(Said 'uncover/food,tray')
						)
						(== state 2)
					)
					(if (cast contains: foodLidView)
						(Print 87 19)
					else
						(self cue:)
					)
				)
			)
			((Said 'get/gun<guard') (Print 87 7))
			((Said 'get/gun')
				(cond 
					((== state 4) (self cue:))
					(local1 (Print 87 20))
					(else (Print 87 21))
				)
			)
		)
	)
)

(instance westShootEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 187
					setLoop: 0
					setCel: 0
					setCycle: CT 4 1 self
				)
				(machineGun play:)
			)
			(1
				(ego
					view: 687
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: End
					setScript: 0
				)
				(client setCycle: End)
				(= seconds 4)
			)
			(2 (EgoDead 970 1 0 87 22))
		)
	)
)

(instance westGetsShotScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 287
					setMotion: 0
					setLoop: 1
					cycleSpeed: 1
					cel: 0
					setCycle: End
				)
				(ego setCycle: Beg)
				(backGuard setScript: backShootEgoScript)
				(= theWestGuard backGuard)
			)
		)
	)
)

(instance backShootEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 20))
			(1
				(ego setScript: 0)
				(setDownFoodScript dispose:)
				(client
					view: 187
					setLoop: 6
					setCel: 0
					setCycle: CT 4 1 self
				)
				(machineGun play:)
			)
			(2
				(ego view: 687 loop: 2 cel: 0 cycleSpeed: 1 setCycle: End)
				(client setCycle: End)
				(= seconds 4)
			)
			(3 (EgoDead 970 1 0 87 22))
		)
	)
)

(instance backGetsShotScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 687
					cel: 0
					loop: 0
					setScript: 0
					setCycle: End self
				)
				(setDownFoodScript dispose:)
				(= theWestGuard 0)
				(gunShot play:)
				(globalSound number: 90 play:)
			)
			(1
				(client
					view: 287
					setMotion: 0
					setLoop: 0
					cycleSpeed: 1
					cel: 0
					setCycle: End
				)
				(ego setCycle: Beg)
				(= seconds 3)
			)
			(2
				(Print 87 23 #at 10 10)
				(Print 87 24 #at 10 10)
				(theGame changeScore: 5)
				(HandsOn)
				(ego
					view: 684
					setCycle: Walk
					heading: 270
					loop: 1
					setLoop: Grooper
				)
			)
		)
	)
)

(instance freeAmbassadorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(backGuard ignoreActors: 1)
				(ego setAvoider: Avoid setCycle: Walk)
				(if (< (ego y?) (+ (ambassador y?) 10))
					(ego setMotion: MoveTo 79 116 self)
				else
					(ego setMotion: DPath 97 128 97 110 79 110 79 116 self)
				)
			)
			(1
				(ego view: 187 loop: 7 setCycle: End self)
			)
			(2
				(ego setAvoider: 0)
				(ambassador setCycle: 0 view: 187 setLoop: 5 cel: 0)
				(= seconds 2)
			)
			(3
				(Print 87 25 #at 10 10)
				(Print 87 26 #at 10 10)
				(ego
					view: 684
					loop: 2
					setCycle: Walk
					setMotion: DPath 97 116 97 124 82 124 self
				)
			)
			(4
				(ego view: 187 loop: 8 setCycle: End self)
			)
			(5
				(ambassador cel: 1)
				(ego view: 684 loop: 3 setCycle: Walk)
				(HandsOn)
				(User canControl: 0)
				(= seconds 3)
			)
			(6
				(chairView init:)
				(ambassador setCycle: End self)
			)
			(7
				(ambassador view: 287 loop: 4)
				(Print 87 27 #at 10 10)
				(Print 87 28 #at 10 10)
				(= seconds 4)
			)
			(8
				(Print 87 29 #at 10 10)
				(= seconds 4)
			)
			(9
				(Print 87 30 #at 10 10)
				(= seconds 4)
			)
			(10
				(Print 87 31 #at 10 10)
				(= seconds 3)
			)
			(11
				(client setScript: tookTooLongScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'change<tell<ambassador,man[/clothes]')
					(Said 'change,wear,detach,(get<off)/clothes')
				)
				(client setScript: changeClothesScript)
				(self dispose:)
			)
			((Said 'address/man,ambassador') (= seconds 0) (self cue:))
			((Said 'bind/man,guard') (Print 87 32))
			((Said 'untie/guard') (Print 87 33))
		)
	)
)

(instance moveToGuard of MoveTo
	(properties)
	
	(method (onTarget)
		(if (super onTarget:) else (client isBlocked:))
	)
)

(instance changeClothesScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 15))
				(ambassador
					view: 287
					setLoop: 4
					ignoreActors: 1
					illegalBits: 0
					setMotion: moveToGuard 120 130 self
					setCycle: Walk
				)
			)
			(1
				(ambassador
					setMotion: moveToGuard (+ (westGuard x?) 20) (+ (westGuard y?) 5) self
				)
			)
			(2
				(ambassador
					view: 287
					setLoop: 2
					cycleSpeed: 2
					ignoreActors: 0
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(3
				(ambassador setCycle: End self)
				(westGuard
					view: 287
					loop: 3
					cel: 0
					cycleSpeed: 5
					setCycle: End self
				)
			)
			(4)
			(5
				(ambassador view: 887 loop: 3 setLoop: -1)
				(= seconds 10)
			)
			(6
				(client setScript: tookTooLongScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((or (Said 'exit[/room]') (Said 'go<out'))
				(if (< state 5)
					(Print 87 34)
				else
					(curRoom setScript: leaveRoomScript)
					(client setScript: 0)
				)
			)
			((Said 'address/man,ambassador') (if (< state 5) (Print 87 35) else (Print 87 36)))
		)
	)
)

(instance leaveRoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 87 37 #at 10 10)
				(HandsOff)
				(ego setMotion: MoveTo 104 175 self)
				(ambassador
					cycleSpeed: 0
					setCycle: Walk
					setMotion: Follow ego
				)
			)
			(1 (curRoom newRoom: 86))
		)
	)
)

(instance tookTooLongScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(backGuard
					view: 987
					setLoop: 0
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(1
				(Print 87 38 #at 10 10)
				(backGuard setCycle: CT 4 1 self)
			)
			(2
				(ego view: 687 setLoop: 3 setCel: 0 setCycle: End)
				(backGuard setCycle: End)
				(machineGun play:)
				(= seconds 3)
			)
			(3 (EgoDead 970 1 0 87 39))
		)
	)
)

(instance foodView of View
	(properties
		view 187
		loop 3
	)
	
	(method (init)
		(super init:)
		(self posn: (- (table x?) 28) (table y?) 20)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/platter,food')
				(cond 
					(local1 (Print 87 40))
					((cast contains: foodLidView) (Print 87 41))
					(else (Print 87 42))
				)
			)
			((Said 'get/gun')
				(cond 
					(local1 (AlreadyTook))
					((not (cast contains: foodLidView)) (Print 87 43))
					(else (CantDo))
				)
			)
		)
	)
)

(instance foodLidView of View
	(properties
		view 187
		loop 3
	)
	
	(method (init)
		(super init:)
		(self
			posn: (+ (foodView x?) 3) (+ (foodView y?) 3) (foodView z?)
			setPri: (foodView priority?)
			addToPic:
		)
	)
)

(instance westGuard of Act
	(properties
		y 178
		x 106
		heading 360
		view 787
		loop 3
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/guard,man]>')
				(cond 
					((Said 'look[<at]')
						(if (== view 787)
							(Print 87 44)
						else
							(Print 87 45)
							(Print 87 46 #icon 387 0 0)
							(SolvePuzzle tunisia 413 8 3)
						)
					)
					((Said 'address') (self cue:))
				)
			)
			((Said 'look/ring')
				(if (== view 787)
					(CantSee)
				else
					(Print 87 47 #icon 387 0 0)
				)
			)
			((Said 'get/ring') (DontNeedTo))
		)
	)
)

(instance backGuard of Act
	(properties
		y 110
		x 160
		heading 180
		view 487
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(and (Said '[/guard,man]>') (Said 'look[<at]')) (if (== view 487) (Print 87 44) else (Print 87 45)))
		)
	)
)

(instance ambassador of Act
	(properties
		y 118
		x 79
		view 187
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(and (Said '[/ambassador,man]>') (Said 'look[<at]')) (Print 87 48))
		)
	)
)

(instance chairView of View
	(properties
		view 187
		loop 3
		cel 2
		signal $4010
	)
	
	(method (init)
		(self
			priority: (- (ambassador priority?) 1)
			x: (ambassador x?)
			y: (ambassador y?)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit/chair') (DontNeedTo))
		)
	)
)

(instance table of RPicView
	(properties
		y 152
		x 220
		view 187
		loop 3
		cel 1
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'give,adjust,drop/tray,food[/table]')
				(if (cast contains: foodView)
					(Print 87 49)
				else
					(ego setScript: setDownFoodScript)
				)
			)
			((Said 'look[<at][/table]') (Print 87 50))
		)
	)
)

(instance mosaicPic of RPicView
	(properties
		y 77
		x 177
		view 187
		loop 3
		cel 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wall,painting,mosaic]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 87 51))
				)
			)
		)
	)
)

(instance gunShot of Sound
	(properties
		priority 3
	)
)

(instance machineGun of Sound
	(properties
		priority 3
	)
)
