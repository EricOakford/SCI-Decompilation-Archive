;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room29 0
)

(local
	local0
)
(instance Room29 of Rm
	(properties
		picture 29
	)
	
	(method (init)
		(= horizon 84)
		(= east 30)
		(= west 28)
		(= north 23)
		(super init:)
		(if howFast
			(owlHead setScript: owl init:)
			(owlBody init: stopUpd:)
			(star1 cycleSpeed: 2 setCycle: Fwd init:)
			(star2 init: setScript: twinkle)
		else
			(owlBody init: stopUpd:)
			(owlHead loop: 4 cel: 2 addToPic:)
		)
		(self setRegions: 207 405 setFeatures: owlBody)
		(if (and (== currentAct 3) (< global115 6))
			(self setRegions: 203)
		)
		(switch prevRoomNum
			(23 (ego posn: 151 118))
			(24 (ego posn: 255 125))
		)
		(ego view: 0 illegalBits: -32768 init:)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 29 0))
		(if (& (ego onControl: 0) $0002) (curRoom newRoom: 23))
		(if (& (ego onControl: 0) $0004) (curRoom newRoom: 24))
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if
			(and (== (event type?) evSAID) (Said 'examine>'))
				(cond 
					((Said '[<around,at][/room]') (Print 29 0))
					((Said '/cabin,mansion') (Print 29 1))
					((Said '/path') (Print 29 2))
					((Said '/gazebo') (Print 29 3))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance twinkle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (star2 setCycle: End self))
			(1
				(if (< (Random 1 100) 35)
					(= state -1)
				else
					(= state 0)
				)
				(= seconds (Random 7 15))
			)
		)
	)
)

(instance owl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 7)))
			(1
				(if (== (owlHead cel?) 0)
					(owlHead setCycle: End self)
					(= local0 (Random 0 3))
				else
					(owlHead setCycle: Beg self)
					(= state -1)
				)
			)
			(2
				(if local0 (= cycles 1) else (= seconds (Random 2 5)))
			)
			(3
				(if local0
					(owlHead loop: 4)
					(= seconds (Random 2 5))
				else
					(= state 0)
					(= cycles 1)
				)
			)
			(4
				(owlHead loop: 3)
				(= cycles 5)
			)
			(5
				(if (-- local0) (= state 2) else (= state 0))
				(= cycles 1)
			)
		)
	)
)

(instance star1 of Prop
	(properties
		y 34
		x 294
		view 107
		loop 1
	)
)

(instance star2 of Prop
	(properties
		y 13
		x 117
		view 107
		loop 2
	)
)

(instance owlHead of Prop
	(properties
		y 58
		x 130
		view 125
		loop 3
		priority 14
		signal $0010
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'deliver,feed,hold>')
				(if
				(or (Said '/*<owl') (Said '/owl') (Said '/*/owl'))
					(if theInvItem
						(if haveInvItem (Print 29 4) else (DontHave))
					else
						(Print 29 4)
					)
				)
			)
			((Said 'converse/owl') (Print 29 5))
			((Said 'capture,get/owl') (Print 29 6))
			(
			(or (MousedOn self event 3) (Said 'examine/owl')) (Print 29 7) (event claimed: 1))
		)
	)
)

(instance owlBody of Prop
	(properties
		y 82
		x 122
		view 125
		loop 4
		cel 1
		priority 15
		signal $0010
	)
	
	(method (handleEvent event)
		(if (MousedOn self event 3)
			(Print 29 7)
			(event claimed: 1)
		)
	)
)
