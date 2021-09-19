;;; Sierra Script 1.0 - (do not remove this comment)
(script# 82)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Room82 0
)

(local
	lolotte
	talisman
	heart
	door
	askedWhichKey
	edgar
	local6
	window1
	window2
)
(procedure (UnlockDoor)
	(= unlockedLolotteDoor TRUE)
	(Print 82 0)
	(theGame changeScore: 2)
)

(instance theMusic of Sound
	(properties
		number 45
	)
)

(instance doorSound of Sound
	(properties
		number 300
	)
)

(instance Room82 of Room
	(properties
		picture 82
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 605)
		(Load VIEW 122)
		(if (ego has: iCupidBow)
			(Load VIEW 123)
			(Load VIEW 68)
		)
		(Load VIEW 85)
		(Load VIEW 633)
		(Load VIEW 646)
		(Load SOUND 46)
		(Load SOUND 45)
		(self setRegions: LOLOTTE)
		(super init:)
		(NotifyScript LOLOTTE 0)
		((View new:)
			view: 633
			loop: 4
			cel: 0
			posn: 61 83
			setPri: 4
			addToPic:
		)
		((View new:)
			view: 633
			loop: 4
			cel: 0
			posn: 38 87
			setPri: 5
			addToPic:
		)
		(if isNightTime
			((Prop new:)
				view: 633
				loop: 0
				posn: 62 69
				setPri: 3
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 633
				loop: 1
				posn: 39 73
				setPri: 4
				init:
				setCycle: Forward
			)
			((= window1 (Prop new:))
				view: 646
				loop: 0
				cel: 0
				posn: 92 92
				cycleSpeed: 5
				init:
				stopUpd:
			)
			((= window2 (Prop new:))
				view: 646
				loop: 1
				cel: 0
				posn: 225 93
				cycleSpeed: 5
				init:
				stopUpd:
			)
		)
		(ego
			posn: 233 165
			view: 4
			setStep: 4 2
			baseSetter: 0
			ignoreActors: 0
			illegalBits: -16384
			setPri: -1
			init:
		)
		(if lolotteAlive
			((= lolotte (Actor new:))
				view: 122
				illegalBits: 0
				posn: 165 91
				init:
				stopUpd:
			)
			((= heart (Prop new:))
				view: 123
				posn: (lolotte x?) (- (lolotte y?) 20)
				init:
				hide:
			)
			((= door (Prop new:))
				view: 605
				cel: 0
				posn: 256 155
				init:
				stopUpd:
				ignoreActors: 0
			)
		else
			((= door (Prop new:))
				view: 605
				posn: 256 155
				init:
				stopUpd:
				ignoreActors: 1
			)
			((= lolotte (Actor new:))
				view: 123
				illegalBits: 0
				posn: 165 91
				setLoop: 1
				setCel: 255
				init:
				stopUpd:
			)
			(if (== ((inventory at: iTalisman) owner?) 82)
				((= talisman (View new:))
					view: 123
					loop: 3
					posn: 172 91
					setPri: 6
					init:
				)
			)
			(ego illegalBits: cWHITE)
		)
		(if (== lolotteDoorOpen TRUE)
			(door setCel: 255 ignoreActors: TRUE)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (curRoom newRoom: 88))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((not (& (ego onControl: 0) cLCYAN))
							(cond 
								(
									(or
										(Said 'look[<around][/noword]')
										(Said 'look[<around]/room')
										(Said 'look/castle,tower,bedroom')
									)
									(Print
										(Format @str 82 1
											(if (== (lolotte view?) 122)
												{And speaking of Lolotte...the old bat is sound asleep on her massive bed.}
											else
												{However, since Lolotte is now dead, it doesn't really matter anymore.}
											)
										)
									)
								)
								((Said 'bang')
									(if lolotteAlive
										(Print 82 2)
									else
										(Print 82 3)
									)
								)
								((Said 'look>')
									(cond 
										((Said '<under/bed')
											(Print 82 4)
										)
										((Said '/bed')
											(Print 82 5)
										)
										((Said '/window')
											(if
												(or
													(ego inRect: 66 118 131 131)
													(ego inRect: 196 118 240 135)
												)
												(Print 82 6)
											else
												(NotClose)
											)
										)
										((Said '/wall')
											(Print 82 7)
										)
										((Said '/dirt')
											(Print 82 8)
										)
										((Said '/carpet')
											(Print 82 9)
										)
										((Said '/mirror')
											(if (ego inRect: 42 123 108 144)
												(Print 82 10)
											else
												(NotClose)
											)
										)
										((Said '/stair')
											(Print 82 11)
										)
										((Said '/door')
											(Print 82 12)
										)
										((Said '/lolotte,fairies')
											(if lolotteAlive
												(Print 82 13)
											else
												(Print
													(Format @str 82 14
														(if (== ((inventory at: iTalisman) owner?) 82)
															{\nGenesta's magic talisman hangs from around her neck.}
														else
															{_}
														)
													)
												)
											)
										)
										(else
											(event claimed: FALSE)
										)
									)
								)
								((Said 'help,save/lolotte,fairies')	;EO: fixed said spec
									(Print 82 15)
								)
								((Said 'kiss[/lolotte,fairies]')
									(if lolotteAlive
										(Print 82 16)
									else
										(Print 82 17)
									)
								)
								((Said 'deliver>')
									(Print 82 18)
									(event claimed: TRUE)
								)
								((Said 'awaken/lolotte,fairies')
									(if lolotteAlive
										(curRoom setScript: egoZapped)
									else
										(Print 82 19
											#font smallFont
											#at 15 10
											#time 4
										)
									)
								)
								((Said 'get,capture/lolotte,fairies')
									(if lolotteAlive
										(Print 82 20)
									else
										(Print 82 17)
									)
								)
								((Said 'converse[<lolotte,fairies]')
									(if lolotteAlive
										(curRoom setScript: egoZapped)
									else
										(Print 82 21 #time 5)
									)
								)
								((Said 'blow/whistle')
									(if lolotteAlive
										(curRoom setScript: egoZapped)
									else
										(event claimed: FALSE)
									)
								)
								((Said 'get/amulet')
									(cond 
										((ego has: iTalisman)
											(Print 82 22 #time 3)
										)
										((!= ((inventory at: iTalisman) owner?) 82)
											(Print 82 23 #time 5)
										)
										((not (ego inRect: 120 120 215 135))
											(Print 82 24 #time 3)
										)
										(lolotteAlive
											(curRoom setScript: egoZapped)
										)
										(else
											(Print 82 25 #time 2)
											(ego get: iTalisman)
											(talisman dispose:)
											(= gotItem TRUE)
											(theGame changeScore: 5)
										)
									)
								)
								((Said 'close/door')
									(if (== (door cel?) 0)
										(Print 82 26)
									else
										(Print 82 27)
									)
								)
								(
									(or
										(Said 'lay<down,sleep')
										(Said '(sleep<in,on),(lay<down,in,on),(get<in,on)/bed')
									)
									(Print 82 28)
								)
								((Said 'kill/lolotte,fairies')
									(cond 
										((== lolotteAlive FALSE)
											(Print 82 29)
										)
										((ego has: iCupidBow)
											(Print 82 30)
										)
										(else
											(Print 82 31)
										)
									)
								)
								((Said 'launch[/noword]')
									(Print 82 32)
								)
								((Said 'launch/lolotte,fairies,arrow,arrow')
									(if (ego has: iCupidBow)
										(cond 
											((>= ((inventory at: iCupidBow) loop?) 2)
												(Print 82 33)
											)
											((not lolotteAlive)
												(Print 82 34)
											)
											((== (curRoom script?) lolotteDead)
												(Print 82 35)
											)
											(else
												(self setScript: shootLolotte)
											)
										)
									else
										(Print 82 36)
									)
								)
							)
						)
						((Said 'look/stair')
							(Print 82 37)
						)
						((Said 'look/wall')
							(Print 82 7)
						)
						((Said 'look/door')
							(Print 82 38)
						)
						((Said 'open/window')
							(Print 82 39)
						)
						((Said 'break/window')
							(Print 82 40)
						)
						((Said 'look[<around][/room]')
							(Print 82 41)
						)
						((Said 'unlatch/door>')
							(cond 
								(unlockedLolotteDoor
									(event claimed: TRUE)
									(Print 82 26)
								)
								((not (ego inRect: 233 155 251 162))
									(event claimed: TRUE)
									(NotClose)
								)
								(
									(or
										(and (ego has: iGoldKey) (not (ego has: iSkeletonKey)))
										(and (Said 'anyword/anyword/(key<gold)') (ego has: iGoldKey))
									)
									(event claimed: TRUE)
									(UnlockDoor)
								)
								((and (Said 'anyword/anyword/key<skeleton') (ego has: iSkeletonKey))
									(Print 82 42)
								)
								((and (ego has: iSkeletonKey) (ego has: iGoldKey))
									(event claimed: TRUE)
									(Print 82 43)
									(= askedWhichKey TRUE)
								)
								(else
									(event claimed: TRUE)
									(Print 82 44)
								)
							)
						)
						((or (Said '[use]/key<gold') (Said '/gold'))
							(cond 
								((not (ego has: iGoldKey))
									(Print 82 45)
								)
								((not (ego inRect: 233 155 251 162))
									(event claimed: TRUE)
									(NotClose)
								)
								(unlockedLolotteDoor
									(Print 82 46)
								)
								((or askedWhichKey (not (ego has: iSkeletonKey)))
									(UnlockDoor)
								)
								(else
									(Print 82 47)
								)
							)
						)
						(
						(or (Said '[use]/key<skeleton') (Said '/skeleton'))
							(if (and (ego has: iSkeletonKey) askedWhichKey (not unlockedLolotteDoor))
								(Print 82 42)
							else
								(Print 82 48)
							)
						)
						((Said 'open/door')
							(if (ego inRect: 233 155 251 162)
								(cond 
									((and unlockedLolotteDoor (== (door cel?) 0))
										(User canControl: FALSE canInput: FALSE)
										(door startUpd: ignoreActors: TRUE setCycle: EndLoop doDoor)
										(ego stopUpd:)
										(doorSound play:)
									)
									((not unlockedLolotteDoor)
										(Print 82 49)
									)
									(else
										(Print 82 50)
									)
								)
							else
								(NotClose)
							)
						)
						((Said 'close/door')
							(if (== (door cel?) 0)
								(Print 82 26)
							else
								(Print 82 27)
							)
						)
					)
				)
			)
		)
	)
)

(instance doDoor of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door stopUpd:)
				(User canControl: TRUE canInput: TRUE)
				(= lolotteDoorOpen TRUE)
				(ego illegalBits: cWHITE startUpd:)
			)
		)
	)
)

(instance lolotteDead of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 46 loop: 1 play:)
				(= cycles 2)
			)
			(1
				(cls)
				(lolotte setCycle: EndLoop self)
			)
			(2
				(Print 82 51 #font smallFont #width 135 #at 2 10)
				(Print 82 52 #font smallFont #width 135 #at 2 10)
				(heart
					loop: 2
					cel: 255
					show:
					cycleSpeed: 4
					setCycle: EndLoop stopHeart
				)
				(lolotte
					view: 123
					loop: 0
					cel: 255
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(3
				(Print 82 53 #at -1 10 #font smallFont #width 250)
				(lolotte loop: 1 cel: 255 cycleSpeed: 3 setCycle: EndLoop self)
			)
			(4
				(Print 82 54 #at -1 10 #width 250 #font smallFont)
				((= talisman (View new:))
					view: 123
					loop: 3
					posn: 172 91
					init:
					setPri: (+ (lolotte priority?) 1)
				)
				(Print 82 55 #at -1 10 #font smallFont #width 250)
				(theGame changeScore: 8)
				(lolotte stopUpd:)
				(= lolotteAlive FALSE) ;ding-dong, the witch is dead!
				(if (< gameHours 30)
					;it's now 6 a.m., only two hours left!
					(= gameHours 30)
					(= gameMinutes 0)
				)
				(= seconds 3)
			)
			(5
				(Print 82 56 #at -1 10 #font smallFont)
				(window1 setCycle: EndLoop self)
				(window2 setCycle: EndLoop)
				(= isNightTime FALSE)
			)
			(6
				(User canControl: TRUE canInput: TRUE)
				(curRoom setScript: edsHere)
			)
		)
	)
)

(instance shootLolotte of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(Face ego lolotte)
				(ego view: 68 cel: 255 setMotion: 0 setCycle: EndLoop self)
			)
			(1
				(Print 82 57 #at -1 10 #font smallFont)
				(= seconds 1)
			)
			(2
				(ego view: 4 setCycle: Walk)
				((Inventory at: iCupidBow) loop: (+ ((Inventory at: iCupidBow) loop?) 1))
				(curRoom setScript: lolotteDead)
			)
		)
	)
)

(instance stopHeart of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(heart dispose:)
			)
		)
	)
)

(instance egoZapped of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(window1 startUpd:)
				(window2 startUpd:)
				(theMusic number: 45 loop: 1 play:)
				(lolotte setCycle: EndLoop self)
			)
			(1
				(lolotte loop: 1 setCycle: Forward)
				(ego
					view: 85
					loop: 0
					cel: 255
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(2
				(lolotte setCycle: 0 cel: 0)
				(ego loop: 1 setCycle: EndLoop self)
			)
			(3
				(Print 82 58 #at 15 5 #font smallFont)
				((ScriptID 0 4) setReal: self 3)
			)
			(4
				(= dead TRUE)
			)
		)
	)
)

(instance edsHere of Script
	(method (init who)
		(Load VIEW 130)
		(Load VIEW 132)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 43 loop: 1 play:)
				(User canControl: FALSE canInput: FALSE)
				(ego setMotion: 0 loop: 0)
				((= edgar (Actor new:))
					view: 130
					illegalBits: 0
					posn: 185 203
					init:
					ignoreActors: FALSE
					setCycle: Walk
					setMotion: MoveTo 251 162 self
				)
			)
			(1
				(edgar
					view: 132
					loop: 1
					cel: 255
					cycleSpeed: 6
					setCycle: CycleTo 3 1 self
				)
				(= seconds 1)
			)
			(2
				(edgar setCycle: CycleTo 0 -1)
				(= seconds 2)
			)
			(3
				(Print 82 59)
				(edgar
					view: 132
					setLoop: 2
					cycleSpeed: 0
					setCycle: EndLoop
				)
				(= seconds 3)
			)
			(4
				(edgar
					view: 130
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 185 205 self
				)
			)
			(5
				(User canControl: TRUE canInput: TRUE)
				(edgar dispose:)
				(= state -1)
			)
		)
	)
)
