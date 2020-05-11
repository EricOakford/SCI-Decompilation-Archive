;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room48 0
)

(local
	axe
	[local1 3]
	ogress
	local5
)
(instance Room48 of Room
	(properties
		picture 48
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(super init:)
		(if ((Inventory at: iAxe) ownedBy: 48) (Load VIEW 501))
		(Load VIEW 2)
		(Load VIEW 647)
		(Load VIEW 245)
		(Load VIEW 48)
		(ogressChaseMusic init:)
		(ogressCatchMusic init:)
		(switch prevRoomNum
			(49
				(NormalEgo 0)
				(ego posn: 130 123 view: 2 loop: 0 setStep: 3 2 init:)
			)
			(else 
				(NormalEgo 0)
				(ego posn: 135 134 view: 2 loop: 0 setStep: 3 2 init:)
			)
		)
		(self setRegions: OGRE_HOUSE)
		(if isNightTime
			((View new:) view: 647 loop: 0 posn: 158 93 addToPic:)
			((View new:) view: 647 loop: 1 posn: 263 96 addToPic:)
			((Prop new:)
				view: 501
				loop: 1
				posn: 221 140
				setPri: 15
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 501
				loop: 1
				posn: 101 140
				setPri: 15
				init:
				setCycle: Forward
			)
		else
			((View new:)
				view: 501
				loop: 0
				cel: 2
				posn: 221 140
				setPri: 15
				ignoreActors: TRUE
				addToPic:
			)
			((View new:)
				view: 501
				loop: 0
				cel: 2
				posn: 101 140
				setPri: 15
				ignoreActors: TRUE
				addToPic:
			)
		)
		((View new:)
			view: 501
			loop: 0
			cel: 1
			posn: 267 128
			setPri: 11
			ignoreActors: TRUE
			addToPic:
		)
		(if (== ((inventory at: iAxe) owner?) 48)
			((= axe (Prop new:))
				view: 501
				loop: 0
				cel: 0
				posn: 243 124
				init:
				stopUpd:
			)
		)
		((= ogress (Actor new:)) setScript: ogressChase)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 49))
	)
	
	(method (dispose)
		(timers eachElementDo: #dispose 84)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '<under/bed') (Print 48 0))
							((Said '/bed') (Print 48 1))
							((Said '<in/chest,dresser,drawer') (Print 48 2))
							((Said '/chest,dresser,drawer') (Print 48 3))
							((Said '/carpet') (Print 48 4))
							((Said '/window')
								(if
									(or
										(ego inRect: 143 119 178 126)
										(ego inRect: 232 126 275 132)
									)
									(Print 48 5)
								else
									(Print 800 1)
								)
							)
							((Said '/stair') (Print 48 6))
							((Said '/barrel') (Print 48 7))
							((Said '/mirror')
								(if (ego inRect: 201 143 282 170)
									(Print 48 8)
								else
									(Print 800 1)
								)
							)
							((Said '/door')
								(if (== ((inventory at: iMagicHen) owner?) 48)
									(Print 48 9)
								else
									(Print 48 10)
								)
							)
							((Said '/wall') (Print 48 11))
							((or (Said '/dirt') (Said '<down')) (Print 48 12))
							((Said '[<around,at][/room,bedroom,cottage]')
								(Print
									(Format @str 48 13
										(if (== ((inventory at: iAxe) owner?) 48)
											{Against the right wall, leans the ogre's axe.}
										else
											{}
										)
									)
								)
							)
						)
					)
					((Said 'get/ax')
						(if (== ((inventory at: iAxe) owner?) 48)
							(if (< (ego distanceTo: axe) 20)
								(ego get: iAxe)
								(= gotItem TRUE)
								(theGame changeScore: 2)
								(axe dispose:)
							else
								(Print 800 1)
							)
						else
							(Print 48 14)
						)
					)
					(
						(or
							(Said 'sleep')
							(Said '(lay[<in,on,down]),(get[<in,in])[/bed]')
						)
						(Print 48 15)
					)
					((Said 'open/chest,dresser,drawer') (Print 48 2))
					((Said 'open/window')
						(if
							(or
								(ego inRect: 143 119 178 126)
								(ego inRect: 232 126 275 132)
							)
							(Print 48 16)
						else
							(Print 800 1)
						)
					)
					((Said 'open/door')
						(if (ego inRect: 25 137 60 154)
							(Print 48 17)
						else
							(Print 800 1)
						)
					)
					((Said 'unlatch/door')
						(if (ego inRect: 25 137 60 154)
							(Print 48 18)
						else
							(Print 800 1)
						)
					)
					((Said 'break/door')
						(if (ego inRect: 25 137 60 154)
							(Print 48 19)
						else
							(Print 800 1)
						)
					)
					((Said 'bang/door')
						(if (ego inRect: 25 137 60 154)
							(if (== ((inventory at: 33) owner?) 48)
								(Print 48 20)
							else
								(Print 48 21)
							)
						else
							(Print 800 1)
						)
					)
				)
				(if
					(and
						(not (event claimed?))
						(> (ogressChase state?) 0)
					)
					(cond 
						((Said 'look/giantess') (Print 48 22))
						((Said 'converse[/giantess]') (Print 48 23))
						((Said 'kill[/giantess]') (Print 48 24))
						((Said 'get,capture/giantess') (Print 48 25))
						((Said 'deliver') (Print 48 26))
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance ogressChase of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					view: 245
					posn: 0 0
					illegalBits: 0
					ignoreActors: 1
					setPri: 8
					setCycle: Walk
					init:
					hide:
				)
				(if (!= debugOn FALSE) (= seconds 10) else (= seconds 60))
			)
			(1
				(= local5 10)
				(= isHandsOff TRUE)
				(User canInput: 0)
				(playMusic cue:)
				(client
					posn: 64 138
					show:
					setAvoider: Avoider
					setMotion: MoveTo 135 123 self
				)
			)
			(2
				(if (and (< (ego x?) 135) (> (ego y?) 128))
					(ogress setAvoider: Avoider setMotion: MoveTo 145 135 self)
				else
					(self cue:)
				)
			)
			(3
				(Print 48 27)
				(client
					illegalBits: -32768
					setPri: -1
					setAvoider: Avoider
					setMotion: Chase ego 15 self
				)
			)
			(4
				(HandsOff)
				(= local5 11)
				(playMusic cue:)
				(ego hide:)
				(ego hide:)
				(client
					view: 48
					cycleSpeed: 2
					cel: 255
					setCycle: EndLoop self
				)
			)
			(5
				(Print 48 28 #at -1 10)
				(= seconds 4)
			)
			(6 (= dead TRUE))
		)
	)
)

(instance ogressChaseMusic of Sound
	(properties
		number 10
	)
)

(instance ogressCatchMusic of Sound
	(properties
		number 11
	)
)

(instance playMusic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local5
					(10
						(ogressChaseMusic play: self)
					)
					(11
						(ogressCatchMusic play: self)
					)
				)
			)
			(1
				(if (!= local5 11) (= state -1) (self cue:))
			)
		)
	)
)
