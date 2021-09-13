;;; Sierra Script 1.0 - (do not remove this comment)
(script# 134)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm134 0
)

(local
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
		(aSkimmer
			setLoop: 2
			setCycle: Forward
			setPri: 2
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		(aMan
			setLoop: 0
			moveSpeed: 3
			cycleSpeed: 3
			setCycle: Forward
			setCycle: Forward	;why the duplicate line?
			setPri: 3
			setStep: 1 1
			illegalBits: -513
			init:
			setMotion: Wander
		)
		(aFart
			setLoop: 3
			setPri: 5
			illegalBits: 0
			init:
			hide:
			setScript: fartScript
		)
		(aPiss
			setLoop: 4
			setPri: 10
			illegalBits: 0
			init:
		)
		(if ((inventory at: iBikiniTop) ownedBy: curRoomNum)
			(aBra
				stopUpd:
				init:
			)
		)
		(ego
			view: 136
			setLoop: 0
			cel: 0
			illegalBits: cWHITE
			observeControl: cLBLUE
			posn: 88 6
			setPri: 11
			setMotion: 0
			init:
		)
		(User canControl: TRUE canInput: TRUE)
		(= currentStatus egoDIVING)
		(self setRegions: SHIP setScript: rm134Script)
	)
	
	(method (dispose)
		(DisposeScript WANDER)
		(super dispose:)
	)
)

(instance rm134Script of Script
	(method (doit)
		(super doit:)
		(if (== (++ drownTimer) 200)
			(Print 134 0)
		)
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
				(if (and (> (ego heading?) 90) (< (ego heading?) 270))
					(ego setLoop: 0)
				)
				(if (or (< (ego heading?) 90) (> (ego heading?) 270))
					(ego setLoop: 1)
				)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
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
		(if (Said 'get/inner,man')
			(Print 134 5)
		)
		(if (Said '/drain')
			(Print 134 6)
		)
		(if (Said 'bathing,dive,(climb<off)')
			(Print 134 7)
		)
		(if (Said 'get/(top,bikini,job,<bikini)')	;EO: fixed spec error
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
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 22 44))
			)
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

(instance aBra of View
	(properties
		y 135
		x 84
		view 314
		loop 1
		priority 2
		signal ignrAct
	)
)

(instance aSkimmer of Prop
	(properties
		y 27
		x 116
		view 314
		signal ignrAct
	)
)

(instance aMan of Actor
	(properties
		y 19
		x 200
		view 314
	)
)

(instance aPiss of Actor
	(properties
		y 999
		x 999
		view 314
		signal ignrAct
	)
)

(instance aFart of Actor
	(properties
		view 314
		signal ignrAct
	)
)
