;;; Sierra Script 1.0 - (do not remove this comment)
(script# 203)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	clarwand 0
)
(synonyms
	(attorney person fellow)
)

(local
	[local0 14] = [37 151 150 159 35 182 77 163 116 128 280 137 148 175]
	[local14 14] = [0 0 -20 140 -20 176 -20 124 110 105 319 113 259 240]
	[local28 14] = [90 134 340 145 108 240 119 240 -20 136 205 116 -20 170]
	[local42 8] = [22 23 29 30 8 7 6 18]
	local50
)
(instance clarwand of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if (not (& global118 $0008))
			(Load rsFONT 41)
			(LoadMany 132 29 94 95 96)
			(Load rsSCRIPT 406)
			(Load rsVIEW 642)
		)
		(LoadMany 143 243 291)
		(Load rsVIEW 906)
		(= [global377 6] 291)
		(if (== [global368 3] 0)
			(= [global368 3] 700)
			(= global115 0)
		)
		(Clarence illegalBits: -32768 ignoreHorizon: 1)
		(if
			(and
				(== [local42 (- 7 global115)] curRoomNum)
				(>= [global368 3] (* (- 6 global115) 100))
			)
			(= global208 (| global208 $0040))
			(= [global368 3] (- 699 (* global115 100)))
			(= local50 1)
			(Clarence
				setAvoider: ((Avoid new:) offScreenOK: 1)
				setCycle: Walk
				posn: [local0 (* global115 2)] [local0 (+ (* global115 2) 1)]
				setMotion:
					MoveTo
					[local28 (* global115 2)]
					[local28 (+ (* global115 2) 1)]
				init:
			)
			(self setScript: clarActions)
		)
	)
	
	(method (doit)
		(if
			(and
				(== (mod [global368 3] 100) 0)
				(== [local42 (/ [global368 3] 100)] curRoomNum)
			)
			(= local50 1)
			(= global208 (| global208 $0040))
			(= global115 (- 7 (/ [global368 3] 100)))
			(Clarence
				setAvoider: ((Avoid new:) offScreenOK: 1)
				setCycle: Walk
				posn: [local14 (* global115 2)] [local14 (+ (* global115 2) 1)]
				setMotion:
					MoveTo
					[local28 (* global115 2)]
					[local28 (+ (* global115 2) 1)]
				init:
			)
			(self setScript: clarActions)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if local50
			(if (> (Clarence y?) 200)
				(if (== [local42 (- 7 (++ global115))] 22)
					(= [global368 3] 1)
				else
					(= [global368 3] (- 699 (* global115 100)))
				)
			else
				(= [global368 3] (- 620 (* global115 100)))
			)
		)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(if
				(and
					(== (event type?) evSAID)
					global208
					(Said
						'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
					)
				)
				(Clarence setScript: (ScriptID 243 0))
				((Clarence script?) handleEvent: event)
			else
				0
			)
		)
	)
)

(instance clarActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0008))
						(= global118 (| global118 $0008))
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(if
					(and
						(== (Clarence x?) [local28 (* global115 2)])
						(== (Clarence y?) [local28 (+ (* global115 2) 1)])
					)
					(= state 1)
				else
					(= state 0)
				)
				(= cycles 1)
			)
			(2
				(= local50 0)
				(if (== [local42 (- 7 (++ global115))] 22)
					(= [global368 3] 1)
				else
					(= [global368 3] (- 699 (* global115 100)))
				)
				(= global208 (& global208 $ffbf))
				(= [global377 6] 0)
				(Clarence dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance Clarence of Act
	(properties
		view 400
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((Said 'converse/attorney') (Print 203 0))
			(
			(and (MousedOn self event 3) (not (& global207 $0040))) (event claimed: 1) (ParseName {clarence}))
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event 3) (Said 'examine/attorney'))
				)
				(event claimed: 1)
				(Print 203 1)
			)
		)
	)
)
