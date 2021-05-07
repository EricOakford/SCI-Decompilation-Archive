;;; Sierra Script 1.0 - (do not remove this comment)
(script# 241)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rudyslap 0
)
(synonyms
	(drink alcohol)
	(rudolph person fellow)
)

(local
	local0
)
(procedure (localproc_000c)
	(cast eachElementDo: #hide)
	(DrawPic 992 dpOPEN_EDGECENTER TRUE 0)
)

(procedure (localproc_0023)
	(DrawPic 48 dpOPEN_CENTEREDGE TRUE 0)
	(addToPics doit:)
	(cast eachElementDo: #show)
)

(instance Rudy of Act
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
			(and (Btst 51) (Said 'tell[/rudolph]/gertie<about'))
				(= theTalker 9)
				(if (& global123 $0001)
					(if (& global145 $0080)
						(Say 1 241 0)
					else
						(Say 1 241 1)
						(= global145 (| global145 $0080))
						(Rudy setScript: goSee)
					)
				else
					(event claimed: 0)
				)
			)
			(
			(and (MousedOn self event 3) (not (& global207 $0100))) (event claimed: 1) (ParseName {rudy}))
			(
				(and
					(& global207 $0100)
					(or (MousedOn self event 3) (Said 'examine/rudolph'))
				)
				(event claimed: 1)
				(Print 241 2)
			)
		)
	)
)

(instance myMusic of Sound
	(properties)
)

(instance rudyslap of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(Load rsSOUND 112)
		(Load rsSCRIPT 985)
		(LoadMany 143 243 252)
		(LoadMany 128 380 381 460 908)
		(= global208 256)
		(= [global377 8] 252)
		(glass setPri: 11 init: stopUpd:)
		(if (== global126 0)
			(Rudy view: 387 loop: 0 posn: 151 113 init:)
			(Fifi init: ignoreActors: 1)
			(self setScript: slapHim)
		else
			(Rudy init: setScript: drink)
		)
		(myMusic number: 112 loop: 0)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return (event claimed?)))
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine/glass,drink') (Print 241 3))
					((Said 'hear/rudolph') (Print 241 4))
					(
					(or (Said 'get/drink,glass') (Said 'drink/drink')) (Print 241 5))
					((Said 'converse/rudolph')
						(= theTalker 9)
						(switch local0
							(0 (Say 1 241 6))
							(1 (Say 1 241 7))
							(2
								(Say 1 241 8)
								(= theTalker 12)
								(Say 0 241 9)
							)
							(3
								(Say 1 241 10)
								(= theTalker 12)
								(Say 0 241 11)
							)
							(4 (Say 1 241 12))
							(else  (Print 241 13))
						)
						(++ local0)
					)
				)
			else
				0
			)
		)
	)
)

(instance slapHim of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (Fifi x?) -10) (Fifi dispose:))
		(if (and (== state 2) (== (Fifi cel?) 4))
			(myMusic number: 112 loop: 1 priority: 5 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global126 1)
				(Rudy cycleSpeed: 1 loop: 0 setCycle: End self)
			)
			(1
				(Rudy hide:)
				(Fifi posn: 114 113 cel: 0 loop: 2 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(Fifi loop: 0 cycleSpeed: 1 setCycle: End self)
				(Print 241 14 #at 90 20 #font 4 #dispose)
			)
			(3
				(Rudy
					show:
					loop: 1
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
				(Fifi loop: 1 posn: 114 113 cycleSpeed: 1 setCycle: End)
			)
			(4
				(cls)
				(Rudy loop: 2 cycleSpeed: 0 setCycle: Fwd)
				(= seconds 4)
			)
			(5
				(Rudy setCycle: 0)
				(Fifi
					view: 460
					cycleSpeed: 0
					setPri: -1
					setAvoider: (Avoid new:)
					setCycle: Walk
					ignoreActors: 0
					setMotion: MoveTo -10 101 self
				)
			)
			(6
				(cls)
				(Rudy
					view: 380
					setCycle: Walk
					setAvoider: (Avoid new:)
					setMotion: MoveTo 164 145 self
				)
			)
			(7
				(Rudy setScript: drink)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance drink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Rudy
					view: 381
					cycleSpeed: 2
					loop: 3
					cel: 2
					posn: 169 126
					setPri: 11
					illegalBits: 0
					ignoreActors: 1
					setCycle: Beg self
				)
			)
			(1
				(glass hide:)
				(Rudy loop: 2 cel: 3 setCycle: Beg self)
			)
			(2
				(Rudy loop: 1 cel: 0 setCycle: End)
				(= seconds 3)
			)
			(3 (Rudy setCycle: Beg self))
			(4
				(Rudy loop: 2 cel: 0 setCycle: End self)
			)
			(5
				(glass show:)
				(Rudy loop: 3 cel: 0 setCycle: End)
				(if (> (Random 1 100) 40) (= state -1))
				(= seconds (Random 6 12))
			)
			(6
				(Rudy loop: 5 cel: 0 setCycle: End)
				(if (< (Random 1 100) 50) (= state 8))
				(= seconds (Random 3 6))
			)
			(7
				(Rudy loop: 6 cel: 0 setCycle: End)
				(= seconds (Random 3 6))
			)
			(8
				(Rudy loop: 6 cel: 2 setCycle: Beg self)
			)
			(9
				(Rudy cel: 3 loop: 5 setCycle: Beg self)
			)
			(10
				(if (< (Random 1 100) 50) (= state -1))
				(= seconds (Random 3 9))
			)
			(11
				(Rudy loop: 4 setCycle: End)
				(if (< (Random 1 100) 50) (= state 13))
				(= seconds (Random 3 9))
			)
			(12
				(Rudy loop: 9 setCycle: End)
				(= seconds (Random 3 9))
			)
			(13
				(Rudy cel: 3 setCycle: Beg)
				(= seconds (Random 3 9))
			)
			(14
				(Rudy loop: 4 cel: 3 setCycle: Beg)
				(= seconds (Random 3 9))
				(= state -1)
			)
		)
	)
)

(instance goSee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (== (Rudy loop?) 2) (== (Rudy loop?) 1))
					(Rudy loop: 2 cel: 0 setCycle: End self)
				else
					(= cycles 1)
				)
				(if (ego inRect: 160 138 170 146)
					(ego setMotion: MoveTo (ego x?) (- (ego y?) 10))
				)
			)
			(1
				(glass show:)
				(cls)
				(HandsOff)
				(Rudy
					view: 380
					posn: 164 145
					setCycle: Walk
					cycleSpeed: 0
					setPri: -1
					illegalBits: -32768
					setAvoider: ((Avoid new:) offScreenOK: 1)
					setMotion: MoveTo 69 109 self
				)
				(if (ego inRect: 67 107 71 111)
					(ego setMotion: MoveTo (ego x?) (- (ego y?) 10))
				)
			)
			(2
				(Rudy setMotion: MoveTo -20 96 self)
			)
			(3
				(= saveDisabled 1)
				(localproc_000c)
				(= seconds 5)
			)
			(4
				(localproc_0023)
				(Print 241 15 #dispose)
				(Rudy setMotion: MoveTo 164 145 self)
				(= saveDisabled 0)
			)
			(5
				(Face ego Rudy)
				(cls)
				(= theTalker 9)
				(Say 1 241 16)
				(HandsOn)
				(Rudy setAvoider: 0)
				(Rudy setScript: drink)
			)
		)
	)
)

(instance glass of Prop
	(properties
		y 138
		x 151
		view 381
		cel 2
	)
)

(instance Fifi of Act
	(properties
		y 113
		x 121
		view 467
		loop 1
		cel 2
	)
)
