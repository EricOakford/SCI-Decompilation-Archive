;;; Sierra Script 1.0 - (do not remove this comment)
(script# 134)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm134 0
)

(local
	aSkimmer
	aBra
	aMan
	aFart
	aPiss
	pissedInPool
	drownTimer
)
(instance rm134 of Room
	(properties
		picture 134
		horizon 5
		north 34
	)
	
	(method (init)
		(Load VIEW 136)
		(Load VIEW 314)
		(super init:)
		((= aSkimmer (Prop new:))
			view: 314
			setLoop: 2
			setPri: 2
			posn: 116 27
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= aMan (Actor new:))
			view: 314
			setLoop: 0
			setPri: 3
			setCycle: Forward
			moveSpeed: 3
			cycleSpeed: 3
			setCycle: Forward
			setStep: 1 1
			illegalBits: -513
			posn: 200 19
			init:
			setMotion: Wander
		)
		((= aFart (Actor new:))
			view: 314
			setLoop: 3
			setPri: 5
			ignoreActors:
			illegalBits: 0
			init:
			hide:
			setScript: fartScript
		)
		((= aPiss (Actor new:))
			view: 314
			setLoop: 4
			setPri: 10
			illegalBits: 0
			ignoreActors:
			posn: 999 999
			init:
		)
		(if ((inventory at: iBikiniTop) ownedBy: curRoomNum)
			((= aBra (View new:))
				view: 314
				setLoop: 1
				setPri: 2
				posn: 84 135
				ignoreActors:
				stopUpd:
				init:
			)
		)
		(ego
			view: 136
			setLoop: 0
			cel: 0
			illegalBits: -32768
			observeControl: 512
			posn: 88 6
			setPri: 11
			setMotion: 0
			init:
		)
		(User canControl: TRUE canInput: TRUE)
		(= currentStatus egoDIVING)
		(self setRegions: 300 setScript: rm134Script)
	)
)

(instance rm134Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (++ drownTimer) 200) (Print 134 0))
		(if (and (== drownTimer 400) (!= currentStatus egoDYING))
			(= currentStatus egoDYING)
			(ego hide:)
			(Print 134 1 #draw)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (not pissedInPool) (== 5 (Random 1 22)))
					(= pissedInPool TRUE)
					(aPiss
						cel: 0
						posn: (+ (ego x?) 7) (- (ego y?) 41)
						setCycle: EndLoop
					)
				)
				(ego setCycle: EndLoop self)
			)
			(1
				(= cycles 4)
				(if (== pissedInPool TRUE)
					(= pissedInPool 255)
					(aPiss posn: 999 999)
					(Print 134 9)
				)
			)
			(2
				(if
				(and (> (ego heading?) 90) (< (ego heading?) 270))
					(ego setLoop: 0)
				)
				(if
				(or (< (ego heading?) 90) (> (ego heading?) 270))
					(ego setLoop: 1)
				)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/drain,bottom')
				(if ((inventory at: iBikiniTop) ownedBy: curRoomNum)
					(Print 134 2)
				else
					(Print 134 3)
				)
			)
			(if (Said '[/fluid,inner,fluid,man,airport]')
				(Print 134 4)
				(if ((inventory at: iBikiniTop) ownedBy: curRoomNum)
					(Print 134 2)
				)
			)
		)
		(if (Said 'get/inner,man') (Print 134 5))
		(if (Said '/drain') (Print 134 6))
		(if (Said 'bathing,dive,(climb<off)') (Print 134 7))
		(if (Said 'get/(bikini,job,(<bikini))')
			(cond 
				((not ((inventory at: iBikiniTop) ownedBy: curRoomNum))
					(Print 134 8)
				)
				((not (ego inRect: 60 130 105 150))
					(NotClose)
				)
				(else
					(Ok)
					(aBra dispose:)
					(ego get: iBikiniTop)
					(theGame changeScore: 7)
				)
			)
		)
	)
)

(instance fartScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 22 44)))
			(1
				(aFart
					posn: (+ (aMan x?) 8) (- (aMan y?) 4)
					cel: 0
					setCycle: EndLoop self
					show:
				)
			)
			(2
				(aFart hide:)
				(self changeState: 0)
			)
		)
	)
)
