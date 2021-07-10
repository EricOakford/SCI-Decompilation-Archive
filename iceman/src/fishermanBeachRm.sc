;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include sci.sh)
(use Main)
(use Intrface)
(use tunisia)
(use n316)
(use n802)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Sight)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	fishermanBeachRm 0
	stupidAvoider 1
	stupidAvoider2 2
)

(local
	local0 =  1
	local1
	local2
	local3
)
(instance fishermanBeachRm of Rm
	(properties
		picture 70
		north 75
		east 71
		south 45
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 68 70 170 71 270)
		(if (== prevRoomNum 45) (globalSound stop:))
		(ego
			init:
			ignoreActors: 0
			observeControl: 64
			cycleSpeed: 0
		)
		(switch prevRoomNum
			(north
				(ego posn: 112 82 loop: 0)
			)
			(east
				(ego posn: 310 (ego y?) loop: 1)
			)
			(else 
				(ego posn: 200 110 view: 71 loop: 3)
			)
		)
		(self
			setRegions: 310 306
			setFeatures:
				building
				((Clone building)
					x: 243
					y: 26
					z: 0
					nsLeft: 213
					nsTop: 18
					nsRight: 274
					nsBottom: 35
					yourself:
				)
		)
		(wave init:)
		((Clone wave)
			x: 97
			y: 179
			loop: 2
			priority: 14
			isExtra: 1
			init:
		)
		((Clone wave)
			x: 37
			y: 150
			loop: 1
			priority: 11
			isExtra: 1
			init:
		)
		((Clone wave)
			x: 177
			y: 181
			loop: 0
			priority: 14
			isExtra: 1
			init:
		)
		(if (not (& (tunisia flags?) $0008))
			(fisherMan init:)
		)
		(RemoveInvItems curRoomNum 3)
	)
	
	(method (doit)
		(cond 
			((and local0 (& (proc802_0 ego 3) $0040)) (Print 70 0) (= local0 0))
			((== (ego onControl: 1) 16384) (self newRoom: north))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(ego ignoreControl: 64)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]') (Print 70 1))
			((Said 'look/rock') (Print 70 2))
		)
	)
)

(instance fisherMan of Prop
	(properties
		y 159
		x 80
		heading 180
		view 68
		loop 3
		priority 13
	)
	
	(method (init)
		(super init:)
		(self setScript: fisherManScript)
		(fisherMansHead init:)
	)
)

(instance fisherManScript of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(local1
				(HandsOff)
				(client setCycle: 0)
				(= seconds 0)
				(theGame changeScore: 1)
				(= local1 0)
				(self setScript: acknowledgeScript self)
			)
			(local2
				(HandsOff)
				(client setCycle: 0)
				(= local2 (= seconds 0))
				(self setScript: giveDirectionScript self)
			)
			(local3
				(HandsOff)
				(client setCycle: 0)
				(= local3 (= seconds 0))
				(self setScript: wontTalkScript self)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 3 cycleSpeed: 3)
				(= seconds (Random 2 6))
			)
			(1 (client setCycle: End self))
			(2 (client setCycle: Beg self))
			(3 (self init: client))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'give/password') (Print 70 3))
			(
			(Said '[/man,iceman,(man<ice),disguise,fisherman]>')
				(cond 
					((CantBeSeen client ego 270)
						(cond 
							((Said 'look[<at]/man,fisherman') (Print 70 4))
							((Said 'address') (Print 70 5))
							((Said '/iceman') (Print 70 6))
						)
					)
					((Said 'look[<at]') (Print 70 7))
					((> (ego distanceTo: fisherMan) 35) (Print 70 8) (event claimed: 1))
					((Said 'get/disguise')
						(if (IsInvItemInRoom curRoomNum 3)
							(Print 70 9)
						else
							(= local2 1)
						)
					)
					((or (ego has: 3) (ego has: 4) (ego has: 5)) (Print 70 10) (event claimed: 1))
					((Said '/iceman,(man<ice)')
						(if (IsInvItemInRoom curRoomNum 3)
							(= local1 1)
						else
							(Print 70 11)
							(event claimed: 1)
						)
					)
					((Said 'address[/man,fisherman]') (= local3 1))
				)
			)
		)
	)
)

(instance fisherMansHead of Prop
	(properties
		view 68
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self
			setPri: (+ (fisherMan priority?) 1)
			posn: (+ (fisherMan x?) 3) (- (fisherMan y?) 27)
			hide:
		)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance acknowledgeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(InitArab ego)
				(= seconds 3)
			)
			(1
				(DisposeArab)
				(Print 70 12 #at 25 10)
				(Print 70 13 #at 25 10)
				(fisherMansHead show: loop: 1 setCycle: End self)
				(fisherMan loop: 2 setCel: 0)
			)
			(2
				(fisherMansHead setCycle: Beg self)
			)
			(3
				(fisherMansHead hide:)
				(fisherMan loop: 3)
				(= cycles 5)
			)
			(4
				(ego view: 71)
				(if (< (ego y?) 137)
					(ego setMotion: MoveTo 113 137 self)
				else
					(= cycles 1)
				)
			)
			(5
				(if (< (ego y?) 152)
					(ego setMotion: MoveTo 109 154 self)
				else
					(= cycles 1)
				)
			)
			(6
				(ego ignoreControl: -32768 setMotion: MoveTo 108 157 self)
			)
			(7
				(ego heading: 270 cycleSpeed: 1)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 1)
			)
			(8
				(Print 70 14)
				(fisherMansHead loop: 0 show: setCel: 0 setCycle: End)
				(fisherMan
					loop: 2
					setCel: 0
					cycleSpeed: 0
					setCycle: End self
				)
			)
			(9
				(ego view: 170 loop: 0 cycleSpeed: 0 setCycle: End self)
			)
			(10
				(ego
					get: 3
					loop: 1
					setCel: 16
					cycleSpeed: 0
					setCycle: Beg self
				)
				(fisherMan loop: 4 cel: 0 cycleSpeed: 1 setCycle: End)
				(fisherMansHead hide:)
			)
			(11
				(fisherMan loop: 3 cel: 0 cycleSpeed: 3)
				(ego
					view: 71
					loop: 1
					setAvoider: 0
					cel: 0
					setCycle: Walk
					observeControl: -32768
				)
				(tunisia flags: (| (tunisia flags?) $0008))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance giveDirectionScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(InitArab ego)
				(= seconds 3)
			)
			(1
				(DisposeArab)
				(= cycles 1)
				(Print 70 15 #at 25 10)
				(Print 70 16 #at 25 10)
			)
			(2
				(fisherMan
					view: 68
					loop: 4
					cycleSpeed: 1
					setCel: 16
					setCycle: Beg self
				)
			)
			(3
				(fisherMan setCycle: End self)
			)
			(4 (= seconds 3))
			(5
				(fisherMan
					view: 68
					loop: 3
					setCel: (fisherMan lastCel:)
					setCycle: Beg self
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance wontTalkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(InitArab ego)
				(= seconds 3)
			)
			(1
				(DisposeArab)
				(Print 70 17 #at 25 10)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance wave of Extra
	(properties
		y 126
		x 270
		view 70
		loop 3
		priority 8
		cycleSpeed 3
		cycleType 1
		minPause 5
		maxPause 5
		minCycles 2
		maxCycles 3
	)
	
	(method (init)
		(super init:)
		(self isExtra: 1)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wave]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 70 18))
				)
			)
		)
	)
)

(instance building of RFeature
	(properties
		y 37
		x 139
		nsTop 30
		nsLeft 113
		nsBottom 44
		nsRight 165
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 70 19))
				)
			)
		)
	)
)

(instance stupidAvoider of Feature
	(properties
		y 104
		x 340
	)
)

(instance stupidAvoider2 of Feature
	(properties
		y 78
		x 340
	)
)
