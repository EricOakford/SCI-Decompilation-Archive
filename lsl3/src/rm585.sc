;;; Sierra Script 1.0 - (do not remove this comment)
(script# 585)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm585 0
)

(instance rm585 of Room
	(properties
		picture 585
	)
	
	(method (init)
		(HandsOff)
		(Load SOUND 485)
		(Load SCRIPT DPATH)
		(Load PICTURE 99)
		(super init:)
		(aOldMan init:)
		(aWhipper init:)
		(aDrummer1 init:)
		(aDrummer2 init:)
		(aSlave init:)
		(aCornMan init:)
		(aWoman init:)
		(ego
			illegalBits: 0
			ignoreActors:
			view: 582
			setLoop: 5
			setCycle: Walk
			setStep: 2 2
			setPri: -1
			posn: 332 161
			init:
		)
		(self setScript: RoomScript)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (> machineSpeed 16)
			(switch (Random 1 22)
				(1 (aOldMan setCycle: Forward))
				(2 (aOldMan stopUpd:))
				(3 (aWhipper setCycle: EndLoop))
				(4 (aWhipper stopUpd:))
				(5 (aDrummer1 setCycle: Forward))
				(6 (aDrummer1 stopUpd:))
				(7 (aDrummer2 setCycle: Forward))
				(8 (aDrummer2 stopUpd:))
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(Print 585 0)
				(ego
					setMotion: DPath
						343 162 343 162 294 149 222 112 206 80
						201 72 201 72 197 58 190 48
				)
				(aWoman
					setMotion: DPath 329 166 306 154 265 139 207 107 195 78 192 63 187 54 self
				)
			)
			(2
				(curRoom drawPic: 99 IRISIN)
				(cast eachElementDo: #hide)
				(= seconds 3)
			)
			(3
				(Print 585 1)
				(= seconds 3)
			)
			(4
				(Print 585 2 #at -1 144)
				(= seconds 3)
			)
			(5
				(Print 585 3)
				(Print 585 4)
				(= seconds 3)
			)
			(6
				(Print 585 5 #at -1 144)
				(= seconds 3)
			)
			(7
				(Print 585 6)
				(= seconds 3)
			)
			(8
				(music number: 485 loop: -1 play:)
				(DisposeScript DPATH)
				(curRoom newRoom: 590)
			)
		)
	)
)

(instance aBowl of View
	(properties
		y 114
		x 284
		view 584
		loop 7
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: stopUpd:)
	)
)

(instance aDrummer1 of Prop
	(properties
		y 55
		x 148
		view 585
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward stopUpd:)
	)
)

(instance aDrummer2 of Prop
	(properties
		y 57
		x 255
		view 585
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward stopUpd:)
	)
)

(instance aOldMan of Prop
	(properties
		y 107
		x 287
		view 584
		loop 6
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance aWhipper of Prop
	(properties
		y 138
		x 86
		view 584
		loop 1
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance aSlave of Prop
	(properties
		y 135
		x 134
		view 585
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward stopUpd:)
	)
)

(instance aCornMan of Actor
	(properties
		y 89
		x 89
		view 584
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 3
			setLoop: 2
			setCycle: Walk
			ignoreActors:
			setScript: ManScript
		)
	)
)

(instance ManScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(aCornMan setMotion: MoveTo 159 87 self)
			)
			(1
				(aCornMan setMotion: DPath 170 90 233 100 261 113 self)
			)
			(2
				(aCornMan setPri: -1 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(aCornMan setLoop: 4 setCycle: EndLoop self)
				(aBowl init:)
			)
			(4
				(aCornMan
					setCycle: Forward
					setLoop: 5
					setMotion: DPath 233 100 170 90 122 84 self
				)
			)
			(5
				(RoomScript changeState: 1)
				(aCornMan setPri: 3 setMotion: MoveTo 84 91 self)
			)
			(6
				(aCornMan hide:)
			)
		)
	)
)

(instance aWoman of Actor
	(properties
		y 163
		x 330
		view 583
	)
	
	(method (init)
		(super init:)
		(self
			setStep: 2 2
			setLoop: 5
			setCycle: Walk
			illegalBits: 0
		)
	)
)
