;;; Sierra Script 1.0 - (do not remove this comment)
(script# COMPOUND)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Grooper)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	inCompoundRm 0
)

(instance inCompoundRm of Room
	(properties
		picture 87
		style DISSOLVE
		vanishingX 117
		vanishingY 21
	)
	
	(method (init)
		(LoadMany VIEW 85 684 187 287 387 487 787 987)
		(LoadMany SCRIPT AVOIDER SIGHT)
		(super init:)
		(ego
			view: 85
			posn: 126 179
			loop: 3
			ignoreActors: 1
			illegalBits: 0
			cycleSpeed: 0
			init:
			setCycle: Walk
			setScript: setDownFoodScript
		)
		(addToPics add: table mosaicPic init: doit:)
		(self setScript: messageScript)
		(backGuard init:)
		(westGuard
			init:
			setCycle: Walk
			setLoop: GradualLooper
			setScript: westGuardScript
		)
		(ambassador init:)
	)
)

(instance messageScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(Print 87 0 #at 65 210 #dispose)
				(self dispose:)
			)
		)
	)
)

(instance westGuardScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: MoveTo 97 136 self)
			)
			(1
				(client heading: 180)
				((client looper?) doit: client (client heading?))
				(self dispose:)
			)
		)
	)
)

(instance setDownFoodScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) (- (ego y?) 20) self)
			)
			(1
				(ego
					setAvoider: Avoider
					setMotion: MoveTo (- (table x?) 40) (- (table y?) 3) self
				)
			)
			(2
				(ego
					view: 187
					loop: 1
					ignoreControl: -32768
					heading: 90
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(3
				(foodView init:)
				(ego setAvoider: 0)
				(= seconds 3)
			)
			(4
				(westGuard setMotion: MoveTo 155 139 self)
			)
			(5
				(DisposeScript AVOIDER)
				(DisposeScript SIGHT)
				(DisposeScript GROOPER)
				(curRoom newRoom: ENDICE)
			)
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

(instance westGuard of Actor
	(properties
		y 178
		x 106
		heading 360
		view 787
		loop 3
	)
)

(instance backGuard of Actor
	(properties
		y 110
		x 160
		heading 180
		view 487
		loop 2
	)
)

(instance ambassador of Actor
	(properties
		y 118
		x 79
		view 187
		loop 4
	)
)

(instance chairView of View
	(properties
		view 187
		loop 3
		cel 2
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(self
			priority: (- (ambassador priority?) 1)
			x: (ambassador x?)
			y: (ambassador y?)
		)
		(super init:)
	)
)

(instance table of PicView
	(properties
		y 152
		x 220
		view 187
		loop 3
		cel 1
		signal ignrAct
	)
)

(instance mosaicPic of PicView
	(properties
		y 77
		x 177
		view 187
		loop 3
		cel 4
	)
)
