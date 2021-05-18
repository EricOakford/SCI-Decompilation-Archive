;;; Sierra Script 1.0 - (do not remove this comment)
(script# 267)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	dusting 0
)
(synonyms
	(fifi person girl)
)

(local
	fifiDir
	talkCount
	toXY = [96 145 216 143 165 155 189 104 111 104 34 92]
	local14
)
(instance dusting of Region
	
	(method (init)
		(super init:)
		(= global192 1)
		(Load FONT 4)
		(LoadMany 143 243 294)
		(LoadMany VIEW 470 904)
		(= global208 16)
		(= [global377 4] 294)
		(Fifi
			view: 464
			setAvoider: ((Avoider new:) offScreenOK: TRUE)
			init:
			setScript: fifiActions
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance fifiActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== fifiDir 3)
					(Fifi setMotion: MoveTo 177 144 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if (== fifiDir 3)
					(Fifi setMotion: MoveTo 170 124 self)
				else
					(= cycles 1)
				)
			)
			(2
				(= local14 0)
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: FALSE
					setMotion:
						MoveTo
						[toXY (* fifiDir 2)]
						[toXY (+ (* fifiDir 2) 1)]
						self
				)
			)
			(3
				(Fifi
					view: 470
					cel: 0
					loop:
					(switch fifiDir
						(0 4)
						(1 4)
						(2 5)
						(3 1)
						(4 0)
						(5 1)
					)
					setCycle: EndLoop self
				)
			)
			(4
				(Fifi
					loop:
					(switch fifiDir
						(0 6)
						(1 6)
						(2 7)
						(3 3)
						(4 2)
						(5 3)
					)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(5
				(Fifi
					cel: 2
					loop:
					(switch fifiDir
						(0 4)
						(1 4)
						(2 5)
						(3 1)
						(4 0)
						(5 1)
					)
					setCycle: BegLoop self
				)
			)
			(6
				(Fifi view: 464 setCycle: Walk ignoreActors: FALSE)
				(if (< fifiDir 5)
					(++ fifiDir)
					(= state -1)
				)
				(= cycles 1)
			)
			(7
				(Fifi setMotion: MoveTo -20 98 self)
			)
			(8
				(= global192 2)
				(Fifi dispose:)
			)
		)
	)
)

(instance Fifi of Actor
	(properties
		y 140
		x 196
	)
	
	(method (handleEvent event)
		(cond 
			((and (MousedOn self event shiftDown) (not (& global207 $0010)))
				(event claimed: TRUE)
				(ParseName {fifi})
			)
			(
				(and
					(& global207 $0010)
					(or (MousedOn self event shiftDown) (Said 'examine/fifi'))
				)
				(event claimed: TRUE)
				(Print 267 0)
			)
			((and (== (event type?) saidEvent) (Said '*/fifi>'))
				(cond 
					((Said 'converse')
						(= theTalker talkFIFI)
						(switch talkCount
							(0 (Say 1 267 1))
							(1 (Say 1 267 2))
							(2 (Say 1 267 3))
							(3 (Say 1 267 4))
							(else  (Say 1 267 5))
						)
						(++ talkCount)
					)
					((Said 'hear')
						(Print 267 6)
					)
				)
			)
		)
	)
)
