;;; Sierra Script 1.0 - (do not remove this comment)
(script# MINSTREL)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	regMinstrel 0
	notes 1
)
(synonyms
	(man man man man boy person bard)
	(kiss kiss embrace)
)

(local
	local0
	minstrelResponse
)
(instance thisSong of Sound)

(instance notes of Prop
	(method (cue)
		(notes dispose:)
	)
)

(instance regMinstrel of Region
	(method (init)
		(super init:)
		(if
			(or
				(and (== curRoomNum 13) (== whereIsMinstrel minstrel13))
				(and (== curRoomNum 14) (== whereIsMinstrel minstrel14))
				(and (== curRoomNum 19) (== whereIsMinstrel minstrel19))
			)
			(minstrel setScript: minstrelActions)
			(notes
				view: 888
				ignoreActors:
				cycleSpeed: 1
				setCycle: Forward
				posn: (+ (minstrel x?) 16) (- (minstrel y?) 20)
				init:
			)
			(thisSong number: 19 play: minstrelActions)
			(minstrelActions changeState: 1)
		)
		(= noWearCrown TRUE)
	)
	
	(method (dispose)
		(= noWearCrown FALSE)
		(thisSong dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp index)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(and
					(== (event type?) saidEvent)
					(cast contains: minstrel)
				)
				(cond 
					((Said 'get/man')
						(Print 516 0)
					)
					((Said 'kiss')
						(Print 516 1)
					)
					((Said 'deliver>')
						(cond 
							((Said '/book')
								(if (ego has: iShakespeareBook)
									(if (>= (ego distanceTo: minstrel) 25)
										(Print 516 2)
									else
										(sounds eachElementDo: #stop 0)
										(minstrelActions changeState: 10)
									)
								else
									(DontHave)
								)
							)
							(
								(and
									(= index (inventory saidMe:))
									(ego has: (inventory indexOf: index))
								)
								(Print 516 3)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'rob/lute')
						(Print 516 4)
					)
					((Said 'look/man')
						(if ((Inventory at: iShakespeareBook) ownedBy: 203)
							(if (cast contains: minstrel)
								(Print 516 5)
							else
								(Print 516 6)
							)
						else
							(Print 516 7)
						)
					)
					((Said 'converse[/man]')
						(cond 
							(((Inventory at: iLute) ownedBy: 203)
								(++ minstrelTalkCount)
								(sounds eachElementDo: #stop 0)
								(if (> minstrelTalkCount 4)
									(= minstrelResponse (Random 2 4))
								else
									(= minstrelResponse minstrelTalkCount)
								)
								(switch minstrelResponse
									(1
										(Print 516 8)
										(Print 516 9)
										(thisSong number: 17 play: minstrelActions)
									)
									(2
										(Print 516 10)
										(thisSong number: 16 play: minstrelActions)
									)
									(3
										(Print 516 11)
										(thisSong number: 15 play: minstrelActions)
									)
									(4
										(Print 516 12)
										(thisSong number: 18 play: minstrelActions)
									)
								)
								(minstrelActions changeState: 1)
							)
							((cast contains: minstrel)
								(Print 516 13)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'get/lute')
						(if ((Inventory at: iLute) ownedBy: 203)
							(Print 516 14)
						else
							(Print 516 15)
						)
					)
					((Said 'hum/')
						(Print 516 16)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance minstrelActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(notes show: setPri: (minstrel priority?))
				(minstrel cycleSpeed: 1 setCycle: Forward)
			)
			(2
				(minstrel setCycle: 0 cel: 0)
				(notes hide:)
			)
			(10
				(notes hide:)
				(HandsOff)
				(Print 516 17)
				(minstrel
					view: 173
					cel: 0
					loop: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(= whereIsMinstrel -1)
				(= seconds 4)
			)
			(11
				(Print 516 18)
				(= gotItem TRUE)
				((Inventory at: iShakespeareBook) moveTo: 203)
				((Inventory at: iLute) moveTo: ego)
				(theGame changeScore: 3)
				(HandsOn)
				(minstrel
					view: 171
					cel: 0
					loop: 2
					xStep: 2
					cycleSpeed: 0
					yStep: 1
					setAvoider: Avoider
					setCycle: Walk
				)
				(switch curRoomNum
					(13
						(minstrel setMotion: MoveTo 329 125 self)
					)
					(14
						(minstrel setMotion: MoveTo 329 136 self)
					)
					(19
						(minstrel setMotion: MoveTo 329 125 self)
					)
				)
			)
			(12
				(minstrel setScript: 0)
				(minstrel dispose:)
			)
		)
	)
)
