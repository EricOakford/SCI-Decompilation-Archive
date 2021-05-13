;;; Sierra Script 1.0 - (do not remove this comment)
(script# 383)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	kissact3 0
)
(synonyms
	(fifi girl)
	(butler fellow)
)

(local
	jeevesToXY = [107 139 163 139 230 139 98 155 175 155 258 155]
	fifiToXY = [163 94 222 112 177 92 90 94]
	randJeeves
	randFifi
	jeevesTalkCount
	fifiTalkCount
)
(instance myMusic of Sound)

(instance kissact3 of Region
	
	(method (init)
		(super init:)
		(Bset fSawFifiJeevesTogether)
		(LoadMany VIEW 467 470 447 459)
		(Load FONT 4)
		(Load SCRIPT AVOIDER)
		(Load SOUND 51)
		(= global192 1)
		(LoadMany 143 243 294 377)
		(LoadMany VIEW 470 904 910)
		(= global208
			(| (|= global208 $0010) $0400)
		)
		(= [global377 4] 294)
		(= [global377 10] 377)
		(myMusic number: 112 loop: 0)
		(Duster illegalBits: 0 ignoreActors: TRUE init:)
		(Fifi setAvoider: (Avoider new:) init:)
		(self setScript: kiss)
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
		(if (event claimed?) (return))
	)
)

(instance kiss of Script
	
	(method (doit)
		(if (and (== state 5) (== (Fifi cel?) 4))
			(Duster dispose:)
		)
		(if (and (== state 5) (== (Fifi cel?) 1))
			(Jeeves loop: 8 cel: 0 setCycle: EndLoop)
		)
		(if
			(and
				(== state 5)
				(== (Jeeves cel?) 1)
				(== (Jeeves loop?) 8)
			)
			(Jeeves loop: 9 setCycle: Forward)
		)
		(if (and (== state 1) (== (Fifi cel?) 1))
			(Duster
				setStep: 3 3
				setLoop: 1
				setCycle: Forward
				setMotion: MoveTo 136 142
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= cycles 1)
			)
			(1
				(Fifi
					view: 475
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(myMusic number: 51 loop: 1 play:)
				(Duster setCycle: 0 loop: 2 cel: 0)
				(Fifi loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(Fifi loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(4
				(Fifi cel: 0 loop: 5 setCycle: Forward)
				(Jeeves setCycle: EndLoop setAvoider: (Avoider new:) init:)
				(= seconds 3)
			)
			(5
				(Fifi loop: 7 setCycle: EndLoop self)
			)
			(6
				(HandsOn)
				(Fifi cycleSpeed: 0 setScript: fifiActions)
				(Jeeves cycleSpeed: 0 setScript: jeevesActions)
				(client setScript: 0)
			)
		)
	)
)

(instance jeevesActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= randJeeves (Random 0 5))
				(Jeeves
					view: 447
					setCycle: Walk
					ignoreActors: FALSE
					setMotion:
						MoveTo
						[jeevesToXY (* randJeeves 2)]
						[jeevesToXY (+ (* randJeeves 2) 1)]
						self
				)
			)
			(1
				(Jeeves
					view: 459
					cel: 0
					loop: (if (< (Random 1 100) 50) 0 else 1)
					setCycle: EndLoop self
				)
			)
			(2
				(Jeeves
					loop: (if (== (Jeeves loop?) 0) 2 else 3)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(3
				(Jeeves
					cel: 2
					loop: (if (== (Jeeves loop?) 2) 0 else 1)
					setCycle: BegLoop self
				)
				(= state -1)
			)
		)
	)
)

(instance fifiActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: 0
					setMotion: MoveTo 64 139 self
				)
			)
			(1
				(Fifi setMotion: MoveTo 56 103 self)
			)
			(2
				(= randFifi (Random 0 3))
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: 0
					setMotion:
						MoveTo
						[fifiToXY (* randFifi 2)]
						[fifiToXY (+ (* randFifi 2) 1)]
						self
				)
			)
			(3
				(Fifi
					view: 470
					cel: 0
					loop:
					(switch randFifi
						(0 1)
						(1 0)
						(2 1)
						(else  0)
					)
					setCycle: EndLoop self
				)
			)
			(4
				(Fifi
					loop:
					(switch randFifi
						(0 3)
						(1 2)
						(2 3)
						(else  2)
					)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(5
				(Fifi cel: 2 setCycle: BegLoop self)
				(= state 1)
			)
		)
	)
)

(instance Jeeves of Actor
	(properties
		y 153
		x 169
		view 475
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(not (& global207 $0400))
					(or (MousedOn self event shiftDown) (Said 'examine/butler'))
				)
				(= theTalker talkJEEVES)
				(|= global207 $0400)
				(event claimed: TRUE)
				(Say 0 383 0)
			)
			(
				(and
					(& global207 $0400)
					(or (MousedOn self event shiftDown) (Said 'examine/butler'))
				)
				(event claimed: TRUE)
				(Print 383 1)
			)
			((Said 'converse,examine/person')
				(Print 383 2)
			)
			((Said 'converse/people')
				(Print 383 3)
			)
			((Said 'examine/people')
				(Print 383 4)
			)
			((Said '/butler>')
				(cond 
					((Said 'converse')
						(= theTalker talkJEEVES)
						(switch jeevesTalkCount
							(0 (Say 1 383 5))
							(1 (Say 1 383 6))
							(2 (Say 1 383 7))
							(else  (Print 383 8))
						)
						(++ jeevesTalkCount)
					)
					((Said 'hear')
						(Print 383 9)
					)
				)
			)
		)
	)
)

(instance Fifi of Actor
	(properties
		y 153
		x 148
		view 475
	)
	
	(method (handleEvent event)
		(if (< (ego distanceTo: Fifi) (ego distanceTo: Jeeves))
			(= global214 16)
		else
			(= global214 1024)
		)
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
				(Print 383 10)
			)
			(
			(and (== (event type?) saidEvent) (Said '*/fifi>'))
				(cond 
					((Said 'converse')
						(= theTalker talkFIFI)
						(switch fifiTalkCount
							(0 (Say 1 383 11))
							(1 (Say 1 383 12))
							(2 (Say 1 383 13))
							(3 (Say 1 383 14))
							(else  (Say 1 383 15))
						)
						(++ fifiTalkCount)
					)
					((Said 'hear')
						(Print 383 16)
					)
				)
			)
		)
	)
)

(instance Duster of Actor
	(properties
		y 124
		x 158
		view 475
		loop 1
	)
)

(instance Sweeper of Prop
	(properties
		y 119
		x 158
		view 475
		loop 2
		cel 2
	)
)
