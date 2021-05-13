;;; Sierra Script 1.0 - (do not remove this comment)
(script# 384)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	WCAct2 0
)

(local
	local0
	local1
)
(instance WCAct2 of Region
	
	(method (init)
		(super init:)
		(LoadMany VIEW 420 402 404)
		(CHead ignoreActors: TRUE setPri: 9 init:)
		(WHead setPri: 9 init:)
		(Clarence setPri: 9 init:)
		(Wilbur init:)
	)
	
	(method (doit)
		(if (and (== (gMySound cel?) 0) (not local1))
			(++ local1)
			(self setScript: walkThru)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (and (== (event type?) saidEvent) (Said '*/c,attorney'))
			(Print 384 0)
		)
	)
)

(instance walkThru of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (not (User canControl:)) (ego mover?))
					(= state -1)
				)
				(= cycles 2)
			)
			(1
				(HandsOff)
				(User canControl: TRUE)
				(Print 384 1 #dispose)
				(= local0 1)
				(CHead hide:)
				(WHead hide:)
				(Clarence
					view: 400
					setCycle: Walk
					setMotion: MoveTo 360 121 self
				)
				(Wilbur
					view: 420
					setCycle: Walk
					setMotion: MoveTo 360 121
				)
			)
			(2
				(cls)
				(User canInput: TRUE)
				(= global154 2)
				(= seconds 30)
			)
			(3
				(= global154 3)
				(client setScript: 0)
			)
		)
	)
)

(instance Clarence of Actor
	(properties
		y 121
		x 209
		view 402
		loop 1
	)
)

(instance Wilbur of Actor
	(properties
		y 121
		x 184
		view 425
	)
)

(instance CHead of Prop
	(properties
		y 121
		x 207
		z 39
		view 404
		loop 2
	)
)

(instance WHead of Prop
	(properties
		y 82
		x 182
		view 425
		loop 4
	)
)
