;;; Sierra Script 1.0 - (do not remove this comment)
(script# 231)
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
	kisscolo 0
)
(synonyms
	(colonel fellow)
	(fifi girl)
	(butt cigarette)
)

(local
	local0
	talkCount
	fifiDir
)
(instance kisscolo of Region
	
	(method (init)
		(super init:)
		(LoadMany FONT 4 41)
		(Load SCRIPT AVOIDER)
		(LoadMany VIEW 470 642 909)
		(LoadMany SOUND 29 51 94 95 96)
		(LoadMany 143 243 225 406)
		(= global208 512)
		(= [global377 9] 225)
		(if (and (== currentAct 0) (== [global368 3] 0))
			(= [global368 3] 1800)
			(LoadMany VIEW 304 464 904)
			(= global195 16)
			(Fifi view: 466 setAvoider: (Avoider new:) init: hide:)
			(Colonel view: 466 init: stopUpd:)
			(self setScript: kiss)
		else
			(if (and (!= [global368 3] 1) (== global124 0))
				(= global195 16)
				(Fifi
					view: 464
					setAvoider: (Avoider new:)
					init:
					illegalBits: (| cWHITE cGREEN)
					setScript: fifiActions
				)
			)
			(Colonel
				view: 304
				init:
				stopUpd:
				setScript: colonelActions
			)
		)
		(Glow
			posn: (+ (Colonel x?) 10) (Colonel y?)
			z: 29
			init:
			hide:
		)
		(smoke1
			posn: (+ (Colonel x?) 6) (- (Colonel y?) 30)
			setPri: (CoordPri (Colonel y?))
			init:
			hide:
		)
		(smoke2
			posn: (+ (Colonel x?) 11) (- (Colonel y?) 24)
			setPri: (CoordPri (Colonel y?))
			init:
			hide:
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
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine/butt')
						(Bset fExaminedCigar)
						(Print 231 0)
					)
					((Said 'hear/fifi,colonel')
						(Print 231 1)
					)
					((Said 'get/butt')
						(Print 231 2)
					)
					((and (Said 'converse>') (Said '/colonel,person'))
						(= theTalker talkCOLONEL)
						(switch talkCount
							(0 (Say 1 231 3))
							(1 (Say 1 231 4))
							(2 (Say 1 231 5))
							(3 (Say 1 231 6))
							(4 (Print 231 7))
						)
						(if (< talkCount 4)
							(++ talkCount)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance kiss of Script

	(method (doit)
		(if (and (== state 1) (== (Colonel cel?) 2))
			(Kiss number: 51 loop: 1 play:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& global118 $0008))
						(|= global118 $0008)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(HandsOff)
				(Colonel cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(Print 231 8
					#at 140 150
					#font 4
					#width 125
					#mode teJustCenter
					#dispose
				)
				(Colonel
					view: 304
					posn: 185 140
					loop: 0
					cel: 0
					cycleSpeed: 0
				)
				(Fifi
					show:
					view: 466
					loop: 1
					cycleSpeed: 2
					ignoreActors: TRUE
					setCycle: Forward
				)
				(= seconds 4)
			)
			(3
				(Fifi cel: 0 loop: 2 setCycle: EndLoop self)
			)
			(4
				(HandsOn)
				(cls)
				(Colonel setScript: colonelActions)
				(Fifi
					view: 470
					loop: 1
					cycleSpeed: 0
					illegalBits: (| cWHITE cGREEN)
					setScript: fifiActions
				)
				(client setScript: 0)
			)
		)
	)
)

(instance fifiActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= fifiDir (Random 0 4))
				(Fifi
					view: 464
					setCycle: Walk
					ignoreActors: FALSE
					setMotion:
						MoveTo
						(switch fifiDir
							(0 225)
							(1 244)
							(2 218)
							(3 130)
							(4 66)
						)
						(switch fifiDir
							(0 126)
							(1 129)
							(2 90)
							(3 94)
							(4 137)
						)
						self
				)
			)
			(1
				(Fifi
					view: 470
					cel: 0
					loop:
					(switch fifiDir
						(0 5)
						(1 0)
						(2 1)
						(3 1)
						(4 5)
					)
					setCycle: EndLoop self
				)
			)
			(2
				(Fifi
					loop:
					(switch fifiDir
						(0 7)
						(1 2)
						(2 3)
						(3 3)
						(4 7)
					)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(3
				(Fifi cel: 2 setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance colonelActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smoke2 cel: 0 hide:)
				(Colonel loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1
				(Glow show: loop: 1 cel: 0 setCycle: Forward)
				(= cycles 18)
			)
			(2
				(Glow hide:)
				(Colonel
					loop: 0
					cel: (- (NumCels Colonel) 1)
					setCycle: BegLoop self
				)
			)
			(3
				(smoke2 setCycle: Forward show:)
				(smoke1 show: setCycle: EndLoop self)
			)
			(4
				(smoke1 cel: 0 hide:)
				(= cycles 1)
			)
			(5
				(if (< (Random 1 100) 30)
					(= state -1)
				else
					(= state 4)
				)
				(= seconds 5)
			)
		)
	)
)

(instance Colonel of Prop
	(properties
		y 140
		x 185
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get,move,press/wheelchair')
				(Print 231 9)
			)
			((and (MousedOn self event shiftDown) (not (& global207 $0200)))
				(event claimed: TRUE)
				(ParseName {colonel})
			)
			(
				(and
					(& global207 $0200)
					(or (MousedOn self event shiftDown) (Said 'examine/colonel'))
				)
				(event claimed: TRUE)
				(Print 231 10)
			)
		)
	)
)

(instance smoke1 of Prop
	(properties
		view 304
		loop 2
	)
)

(instance smoke2 of Prop
	(properties
		view 304
		loop 3
	)
)

(instance Glow of Prop
	(properties
		view 304
		loop 1
	)
)

(instance Fifi of Actor
	(properties
		y 140
		x 206
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'converse,examine/person')
				(Print 231 11)
			)
			((Said 'converse/people')
				(Print 231 12)
			)
			((Said 'examine/people')
				(Print 231 13)
			)
			(
				(or
					(Said 'ask,tell/fifi/*<about')
					(Said 'converse/fifi')
					(Said 'deliver,hold/*<fifi')
					(Said 'deliver,hold/*/fifi')
				)
				(event claimed: FALSE)
				(if (Said 'deliver,hold')
					(if (and theInvItem haveInvItem)
						(Print 231 14)
					else
						(DontHave)
					)
				else
					(Print 231 14)
					(event claimed: TRUE)
				)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/fifi'))
				(if (& global207 $0010)
					(Print 231 15)
				else
					(|= global207 $0010)
					(= theTalker talkFIFI)
					(Say 0 231 16)
				)
				(event claimed: TRUE)
			)
			((Said '/fifi>')
				(cond 
					((Said 'get')
						(Print 231 17)
					)
					((Said 'kill')
						(Print 231 18)
					)
					((Said 'kiss')
						(Print 231 19)
					)
					((Said 'embrace')
						(Print 231 20)
					)
				)
			)
		)
	)
)

(instance Kiss of Sound)
