;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm16 0
)

(local
	keithCallingCaptain
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	goingToHouston
	goingToSteelton
	local11
	local12
	local13
	local14
	local15
	local16
)
(procedure (localproc_000c)
	(if (not local16) (= local16 1) (cSound fade:))
)

(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(instance agent of Actor
	(properties)
)

(instance rm16 of Room
	(properties
		picture 16
		style $0001
		picAngle 70
	)
	
	(method (init)
		(Load VIEW 0)
		(Load VIEW 1)
		(Load VIEW 20)
		(Load VIEW 77)
		(if (>= gamePhase 6)
			(Load VIEW 667)
		)
		(super init:)
		(HandsOn)
		(= perspective 70)
		(if
			(and
				(>= gamePhase 6)
				(< gamePhase 8)
			)
			(Bset fStolenCarTowed)
		)
		(= gunFireState gunPROHIBITED)
		(= gunNotNeeded 1)
		(self setLocales: regFieldKit)
		(ego
			view: (if gunDrawn 7 else 1)
			init:
		)
		(agent
			view: 77
			loop: 2
			posn: 230 110
			setPri: 8
			init:
			ignoreActors:
			illegalBits: 0
			setCycle: Forward
			cycleSpeed:
			stopUpd:
		)
		(switch prevRoomNum
			(12
				(= local4 0)
				(agent
					setLoop: 3
					setCel: 0
					posn: 244 1119
				)
				(ego
					view: 667
					loop: 1
					illegalBits: -32768
					posn: 253 139
					cycleSpeed: 1
				)
				(HandsOff)
				(egoOnThePhoneScript changeState: 6)
			)
			(17
				(ego
					posn: 67 113
					view: (if gunDrawn 6 else 0)
					setMotion: MoveTo 310 113
					setPri: -1
				)
			)
			(18
				(ego
					posn: 285 (ego y?)
					setMotion: MoveTo 10 (ego y?)
				)
			)
			(19
				(ego
					posn: 20 132
					setMotion: MoveTo 100 132
				)
			)
			(else 
				(ego
					posn: 151 188
					setMotion: MoveTo 151 114
				)
				(if (>= gamePhase 6)
					(cSound
						number: 16
						loop: -1
						play:
					)
				)
			)
		)
		(if (Btst fKeithFollows)
			((= keith (Actor new:))
				view: 20
				init:
				setMotion: Follow ego 25
				setAvoider: (Avoider new:)
				setCycle: Walk
			)
			(switch prevRoomNum
				(18
					(keith posn: (+ (ego x?) 20) (ego y?))
				)
				(19
					(keith posn: 91 146)
				)
				(17
					(keith posn: 50 112)
				)
				(else 
					(keith posn: (+ (ego x?) 10) (+ (ego y?) 20))
				)
			)
		)
		(addToPics
			add:
				pictureOnWall
				sittingPerson
				counterPerson
				talking1
				talking2
				talking3
		)
		(addToPics
			add:
				standing1
				standing2
				standing3
				standing4
				standing5
				case
				SIGN
				poster1
				poster2
				agent2
		)
		(addToPics doit:)
		(= local5 1)
	)
	
	(method (doit)
		(cond 
			((ego inRect: 175 112 225 130)
				(if (!= local4 1)
					(= local4 1)
				)
			)
			((ego inRect: 195 130 225 139)
				(if (!= local4 2)
					(= local4 2)
				)
			)
			((ego inRect: 225 139 265 145)
				(if (!= local4 3)
					(= local4 3)
				)
			)
			((ego inRect: 30 114 83 143)
				(if (!= local4 4)
					(= local4 4)
				)
			)
			((ego inRect: 63 130 116 184)
				(if (!= local4 5)
					(= local4 5)
				)
			)
			(else
				(= local4 0)
			)
		)
		(if
			(and
				(>= gamePhase 6)
				(< gamePhase 8)
				local8
			)
			(= local8 0)
			(Bset fKeithFollows)
			((= keith (Actor new:))
				view: 20
				setCycle: Walk
				setAvoider: (Avoider new:)
				init:
				setScript: keithJoinsEgoScript
			)
		)
		(if
			(and
				(Btst fKeithFollows)
				(<= (keith distanceTo: ego) 25)
				(not (Btst fToldKeithAboutRevolver))
				(ego has: iJailerRevolver)
				(== prevRoomNum 19)
			)
			(Bset fToldKeithAboutRevolver)
			(LocPrint 16 0)
			(LocPrint 16 1)
		)
		(if
			(and
				local1
				(ego inRect: 225 139 265 145)
			)
			(egoOnThePhoneScript changeState: 0)
		)
		(if
			(and
				(>= gamePhase 6)
				(!= gamePhase 13)
				(Btst fTriedToGetTicketToHouston)
			)
			(= local13 1)
		)
		(if (== gamePhase 13)
			(= local14 1)
		)
		(if (> local2 1)
			(-- local2)
		)
		(if
			(and
				(== local2 1)
				(not local15)
			)
			(= local2 0)
			(moveScript cue:)
		)
		(if (> local3 0)
			(-- local3)
		)
		(cond 
			(
				(and
					(>= (ego y?) 189)
					(<= 124 (ego x?))
					(<= (ego x?) 178)
				)
				(cSound fade:)
				(= perspective 0)
				(curRoom newRoom: 15)
			)
			(
				(and
					(< (ego y?) 115)
					(< (ego x?) 65)
				)
				(if (== (cSound number?) 16)
					(cSound fade:)
				)
				(= perspective 0)
				(curRoom newRoom: 17)
			)
			((> (ego x?) 305)
				(if (== (cSound number?) 16)
					(cSound fade:)
				)
				(= perspective 0)
				(curRoom newRoom: 18)
			)
			(
				(and
					(< (ego x?) 17)
					(< 128 (ego y?))
					(< (ego y?) 145)
				)
				(if (Btst fKeithFollows)
					(LocPrint 16 2)
				)
				(if (== (cSound number?) 16)
					(cSound fade:)
				)
				(= perspective 0)
				(curRoom newRoom: 19)
			)
		)
		(if
			(and
				(== local4 2)
				(not local5)
			)
			(= local5 1)
			(moveScript changeState: 5)
		)
		(if
			(and (== local4 3)
				(not local5)
			)
			(= local5 1)
			(moveScript changeState: 7)
		)
		(if
			(and
				(!= local4 2)
				(!= local4 3)
				local5
			)
			(if (not local7)
				(moveScript changeState: 0)
				(= local5 0)
			else
				(moveScript changeState: 2)
				(= local5 0)
			)
		)
		(cond 
			((<= (ego y?) 120)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(moveScript dispose:)
		(agentScript dispose:)
		(egoOnThePhoneScript dispose:)
		(keithJoinsEgoScript dispose:)
		(getTheTicketScript dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type:)
			(saidEvent
				(cond
					((Said 'show/shot,mugshot,painting')
						(localproc_000c)
						(cond
							(
								(and
									(not (ego has: 12)) ; new_mug_shot
									(not (ego has: 23)) ; old_mug_shot
								)
								(LocPrint 16 3) ; "You don't have a mug shot of Bains to show."
							)
							(
								(or
									(== local4 5)
									(== local4 4)
									(and
										(or (== local4 3) (== local4 2))
										(not (Btst 39))
									)
									(and (== local4 1) (not (Btst 38)))
								)
								(if (ego has: 12) ; new_mug_shot
									(switch (Random 1 4)
										(1
											(LocPrint 16 4 82 112) ; "Why, that looks just like my Cousin Rufus (minus the corncob pipe, of course). But why are you showing it to me?"
										)
										(2
											(LocPrint 16 5 82 112) ; "Is that your brother? He looks sorta like you. Wait a minute, let me get my glasses....."
										)
										(3
											(LocPrint 16 6 82 112) ; "I'm afraid I don't have the time or the inclination to look at pictures from your family album."
										)
										(4
											(LocPrint 16 7 82 112) ; "Get lost, creep, and take your pictures with you."
										)
									)
								else
									(switch (Random 1 4)
										(1
											(LocPrint 16 4 82 123) ; "Why, that looks just like my Cousin Rufus (minus the corncob pipe, of course). But why are you showing it to me?"
										)
										(2
											(LocPrint 16 5 82 123) ; "Is that your brother? He looks sorta like you. Wait a minute, let me get my glasses....."
										)
										(3
											(LocPrint 16 6 82 123) ; "I'm afraid I don't have the time or the inclination to look at pictures from your family album."
										)
										(4
											(LocPrint 16 7 82 123) ; "Get lost, creep, and take your pictures with you."
										)
									)
								)
							)
							(
								(and
									(== local4 1)
									(ego has: 12) ; new_mug_shot
									(Btst 38)
									(>= gamePhase 6)
								)
								(LocPrint 16 8 82 112) ; "Why, yes..." says the agent. "I remember this guy. He bought a ticket from us recently. He seemed nervous or something."
								(SolvePuzzle 1 81)
							)
							(
								(and
									(or
										(== local4 2)
										(== local4 3)
										(== local4 1)
									)
									(or (Btst 39) (Btst 38))
								)
								(if (ego has: 12) ; new_mug_shot
									(LocPrint 16 9 82 112) ; "I'm sorry, Officer, but the face doesn't ring a bell at all. I wish I could be of more assistance."
								else
									(LocPrint 16 9 82 123) ; "I'm sorry, Officer, but the face doesn't ring a bell at all. I wish I could be of more assistance."
								)
							)
							(else
								(LocPrint 16 10) ; "Nobody can see the mug shot from where you're standing."
							)
						)
					)
					((Said '[look]/computer,computer,show,info[<flight]')
						(cond
							(
								(or
									(and
										(< (ego y:) 114)
										(> (ego x:) 160)
									)
									(and
										(>= (ego y:) 114)
										(> (ego x:) 210)
									)
								)
								(LocPrint 16 11) ; "You can't see the flight info from where you are."
							)
							((not (== (ego loop:) 0))
								(LocPrint 16 12) ; "You're not looking at the monitors."
							)
							(else
								(switch (Random 1 4)
									(1
										(LocPrint 16 13) ; "You see from the display that the next flight departs in 2 minutes, headed for Beirut."
									)
									(2
										(LocPrint 16 14) ; "You see from the display that there will be a flight to Houston shortly."
									)
									(3
										(LocPrint 16 15) ; "You see from the display that the vacation special to Coarsegold departs in seven or eight months."
									)
									(4
										(LocPrint 16 16) ; "You see from the display that there are several flights to Steelton today."
									)
								)
							)
						)
					)
					((Said 'look>')
						(cond
							((Said '/bathroom,(chamber<rest)')
								(LocPrint 16 17) ; "Go on in and look around."
							)
							(
								(Said
									'[<at,around][/chamber,airport,terminal,building,lobby]'
								)
								(LocPrint 16 18) ; "You are inside the new Lytton Airport terminal building. People from all walks of life and all parts of the globe walk by. The little town of Lytton has entered the 20th century. You feel proud that your efforts have helped, even in a small way, to make Lytton a busy, prosperous city."
							)
							((Said '[<at,up][/ceiling]')
								(LocPrint 16 19) ; "If you've seen one ceiling, you've seen 'em all."
							)
							((Said '[<at,down][/floor,dirt,tile]')
								(LocPrint 16 20) ; "The floor tile's shine is marred only by the smeared trail of some child's lost chocolate bar."
							)
							((Said '/pane')
								(cond
									((== (ego loop:) 2)
										(LocPrint 16 21) ; "You look back out at the sidewalk in front of the airport entrance."
									)
									((== (ego loop:) 3)
										(LocPrint 16 22) ; "The runway's out there."
									)
									(else
										(LocPrint 16 23) ; "You're not looking at the window."
									)
								)
							)
							((Said '/flyer,ad')
								(cond
									(
										(or
											(and
												(< (ego y:) 114)
												(> (ego x:) 160)
											)
											(and
												(>= (ego y:) 114)
												(> (ego x:) 210)
											)
											(< (ego x:) 110)
										)
										(LocPrint 16 24) ; "You can't see it from where you are."
									)
									((not (== (ego loop:) 0))
										(LocPrint 16 25) ; "You're not looking at the posters."
									)
									(else
										(LocPrint 16 26) ; "The posters say: "Visit beautiful COARSEGOLD!""
									)
								)
							)
							((Said '/briefcase,bag,baggage')
								(if (== local4 4)
									(LocPrint 16 27) ; "The briefcase appears to belong to the gentleman sitting down."
								else
									(LocPrint 16 28) ; "You're not close enough."
								)
							)
							((Said '/counter,iguana')
								(LocPrint 16 29) ; "There are two agents behind the Iguana Airlines ticket counter."
							)
							((Said '/man,woman,men,women,crowd,passenger')
								(switch local4
									(1
										(LocPrint 16 30) ; "The ticket agent returns your gaze. The lady traveler waits impatiently."
									)
									(3
										(LocPrint 16 31) ; "The agent meets your steady gaze with a smile."
									)
									(2
										(LocPrint 16 31) ; "The agent meets your steady gaze with a smile."
									)
									(4
										(LocPrint 16 32) ; "The man sitting down looks terminally bored."
									)
									(else
										(LocPrint 16 33) ; "Just normal-looking airline passengers...."
									)
								)
							)
							((Said '/agent')
								(if
									(or
										(== local4 2)
										(== local4 3)
										(== local4 1)
									)
									(LocPrint 16 31) ; "The agent meets your steady gaze with a smile."
								else
									(LocPrint 16 28) ; "You're not close enough."
								)
							)
							((Said '<below/bench')
								(if (== local4 4)
									(LocPrint 16 34) ; "The gentleman says "Hey, fella! What're you trying to do?""
								else
									(LocPrint 16 28) ; "You're not close enough."
								)
							)
							((Said '/bench')
								(if (== local4 4)
									(LocPrint 16 35) ; "There are lounge chairs for the convenience of weary travelers. There is an ashtray next to them."
								else
									(LocPrint 16 28) ; "You're not close enough."
								)
							)
							((Said '/wall')
								(LocPrint 16 36) ; "On the wall hangs an abstract painting."
							)
							((Said '/counter')
								(LocPrint 16 37) ; "There are two ticket agents behind the ticket counter."
							)
							((Said '/painting')
								(LocPrint 16 38) ; "This abstract painting, entitled "After the Dust Settles", was created by "Vu", our very favorite computer artist."
							)
							((Said '/ass,cigarette')
								(if (== local4 4)
									(LocPrint 16 39) ; "Nasty-looking things. Some of 'em have lipstick on them."
								else
									(LocPrint 16 28) ; "You're not close enough."
								)
							)
							((Said '/rope')
								(LocPrint 16 40) ; "It's a velveteen braided rope similar to the ones found in some movie theaters."
							)
							((Said '/ashtray,(tray<ash)')
								(if (== local4 4)
									(LocPrint 16 41) ; "You look, and all you see are nasty old butts."
								else
									(LocPrint 16 28) ; "You're not close enough."
								)
							)
							((Said '/list,flight')
								(cond
									(
										(or
											(== local4 0)
											(== local4 4)
											(== local4 5)
										)
										(LocPrint 16 42) ; "No one near you has any of that information."
									)
									(
										(or
											(and (== local4 1) (Btst 38))
											(and
												(or (== local4 2) (== local4 3))
												(Btst 39)
											)
										)
										(agentScript changeState: 1)
									)
									(else
										(LocPrint 16 43) ; "I'm sorry sir," explains the ticket agent, "but the passenger list is confidential information."
									)
								)
							)
						)
					)
					((Said 'kiss')
						(LocPrint 16 44) ; "Not one of your best ideas, Sonny."
					)
					((Said '/hello')
						(LocPrint 16 45) ; "Hi there!"
					)
					((Said '/bye')
						(LocPrint 16 46) ; "So long!"
					)
					((Said 'yes')
						(LocPrint 16 47) ; "Yes...what??"
					)
					((Said 'no')
						(LocPrint 16 48) ; "No...what??"
					)
					((Said 'frisk,kill,hit,fire')
						(LocPrint 16 49) ; "That would be an entirely unprofessional and inappropriate action at this moment."
					)
					((Said 'get,eat,pick[<up]/candy,bar')
						(LocPrint 16 50) ; "The one on the floor?? Y-E-C-C-C-H-H-H-H!!"
					)
					((Said 'give,get/briefcase')
						(cond
							((== ((inventory at: 10) owner:) 16) ; field_kit
								(LocPrint 16 51) ; "It's on its way to the plane."
							)
							((ego has: 10) ; field_kit
								(LocPrint 16 52) ; "You have it."
							)
							(else
								(LocPrint 16 53) ; "You can't get it here."
							)
						)
					)
					((Said 'get,pick[<up]/baggage,briefcase,bag')
						(if (ego inRect: 30 114 83 143)
							(LocPrint 16 54) ; "Hey, buddy!! What do you think you're doing??"
						else
							(LocPrint 16 55) ; "That's uncalled for."
						)
					)
					((Said 'sat[<down]')
						(LocPrint 16 56) ; "You're not weary or tired enough to sit down."
					)
					((Said 'get/bench')
						(if (== local4 4)
							(LocPrint 16 34) ; "The gentleman says "Hey, fella! What're you trying to do?""
						else
							(LocPrint 16 57) ; "What for?"
						)
					)
					((Said 'hoist,move/bench')
						(if (== local4 4)
							(LocPrint 16 34) ; "The gentleman says "Hey, fella! What're you trying to do?""
						else
							(LocPrint 16 57) ; "What for?"
						)
					)
					((Said 'get/painting')
						(LocPrint 16 58) ; "You're a homicide detective....not an art collector."
					)
					((Said 'eat,cigarette,get/ass,cigarette')
						(if (== local4 4)
							(LocPrint 16 59) ; "Not very sanitary."
						else
							(LocPrint 16 60) ; "Huh?"
						)
					)
					((Said 'talk>')
						(cond
							((Said '/man,person')
								(switch local4
									(1
										(localproc_000c)
										(LocPrint 16 61) ; "It sure looks like you're trying to talk to the woman ticket agent."
									)
									(2
										(localproc_000c)
										(LocPrint 16 62) ; "The agent looks at you, pauses, and looks down at some paperwork."
									)
									(3
										(localproc_000c)
										(LocPrint 16 62) ; "The agent looks at you, pauses, and looks down at some paperwork."
									)
									(0
										(LocPrint 16 63) ; "Nobody can hear you from over there."
									)
									(else
										(switch (Random 1 3)
											(1
												(LocPrint 16 64) ; "This person seems to have just arrived from France. You hear, "Je ne parle pas anglais.""
											)
											(2
												(LocPrint 16 65) ; "No one seems to be listening to you."
											)
											(3
												(LocPrint 16 66) ; "Excuse me, but don't you have a flight to catch?"
											)
										)
									)
								)
							)
							((Said '/customer,passenger')
								(if (== local4 1)
									(LocPrint 16 67) ; "She glares at you, obviously upset, but says nothing."
								else
									(LocPrint 16 68) ; "She can't hear you."
								)
							)
							((Said '/men,crowd')
								(if (ego inRect: 63 130 116 184)
									(LocPrint 16 69) ; "They're doing their best to ignore you."
								else
									(LocPrint 16 70) ; "The men in the center of the lobby are talking among themselves."
								)
							)
							((Said '/woman')
								(cond
									((== local4 0)
										(if
											(or
												(and
													(> (ego x:) 225)
													(< (ego y:) 114)
												)
												(< (ego y:) 141)
											)
											(LocPrint 16 63) ; "Nobody can hear you from over there."
										else
											(switch (Random 1 3)
												(1
													(LocPrint 16 64) ; "This person seems to have just arrived from France. You hear, "Je ne parle pas anglais.""
												)
												(2
													(LocPrint 16 65) ; "No one seems to be listening to you."
												)
												(3
													(LocPrint 16 66) ; "Excuse me, but don't you have a flight to catch?"
												)
											)
										)
									)
									((not (Btst 38))
										(localproc_000c)
										(LocPrint 16 71) ; "The agent says, "Please, wait your turn!""
									)
									((== local4 1)
										(localproc_000c)
										(LocPrint 16 72) ; "The agent says, "Yes, officer, what do you want?""
									)
									(else
										(localproc_000c)
										(LocPrint 16 73) ; ""May I help you, sir?" asks the ticket agent."
									)
								)
							)
							((Said '/agent')
								(switch local4
									(1
										(if (Btst 38)
											(LocPrint 16 72) ; "The agent says, "Yes, officer, what do you want?""
										else
											(localproc_000c)
											(LocPrint 16 74) ; "The ticket agent snaps at you, "Wait your turn, Mister! This lady was ahead of you.""
										)
									)
									(2
										(localproc_000c)
										(LocPrint 16 62) ; "The agent looks at you, pauses, and looks down at some paperwork."
									)
									(3
										(localproc_000c)
										(LocPrint 16 62) ; "The agent looks at you, pauses, and looks down at some paperwork."
									)
									(0
										(LocPrint 16 75) ; "The ticket agents can't hear you from over there."
									)
									(5
										(LocPrint 16 75) ; "The ticket agents can't hear you from over there."
									)
									(4
										(LocPrint 16 75) ; "The ticket agents can't hear you from over there."
									)
								)
							)
						)
					)
					((Said 'flash,show/badge')
						(cond
							((not (ego has: 7)) ; wallet
								(LocPrint 16 76) ; "You don't have your identification with you."
							)
							((or (== local4 4) (== local4 5))
								(LocPrint 16 77) ; "What is this....a shakedown?"
							)
							((== local4 1)
								(localproc_000c)
								(Bset 38)
								(agentScript changeState: 0)
							)
							((or (== local4 2) (== local4 3))
								(localproc_000c)
								(Bset 39)
								(agentScript changeState: 0)
							)
							(else
								(LocPrint 16 78) ; "Nobody can see your badge from over there, and besides, what's your purpose?"
							)
						)
					)
					((Said '[*]/bains')
						(LocPrint 16 79) ; "Who's Bains??"
					)
					(
						(or
							(Said 'ask/departure,flight')
							(Said 'is<when/flight<next/houston')
							(Said 'fly,go/houston')
							(Said '/flight,departure<next')
						)
						(if
							(and
								(Btst 34)
								(or (== local4 1) (== local4 2) (== local4 3))
							)
							(agentScript changeState: 4)
						else
							(switch (Random 1 4)
								(1
									(LocPrint 16 13) ; "You see from the display that the next flight departs in 2 minutes, headed for Beirut."
								)
								(2
									(LocPrint 16 14) ; "You see from the display that there will be a flight to Houston shortly."
								)
								(3
									(LocPrint 16 15) ; "You see from the display that the vacation special to Coarsegold departs in seven or eight months."
								)
								(4
									(LocPrint 16 16) ; "You see from the display that there are several flights to Steelton today."
								)
							)
						)
					)
					((or (Said '[get,buy]/ticket') (Said 'give/me/ticket'))
						(event claimed: 0)
						(= local3 100)
						(cond
							((or (== local4 0) (== local4 4) (== local4 5))
								(event claimed: 1)
								(switch (Random 1 4)
									(1
										(LocPrint 16 13) ; "You see from the display that the next flight departs in 2 minutes, headed for Beirut."
									)
									(2
										(LocPrint 16 14) ; "You see from the display that there will be a flight to Houston shortly."
									)
									(3
										(LocPrint 16 15) ; "You see from the display that the vacation special to Coarsegold departs in seven or eight months."
									)
									(4
										(LocPrint 16 16) ; "You see from the display that there are several flights to Steelton today."
									)
								)
							)
							((and (== local4 1) (not (Btst 38)))
								(localproc_000c)
								(event claimed: 1)
								(LocPrint 16 74) ; "The ticket agent snaps at you, "Wait your turn, Mister! This lady was ahead of you.""
							)
							((ego inRect: 225 139 265 145)
								(event claimed: 1)
								(localproc_000c)
								(LocPrint 16 80) ; "Would you please step around to the front of the counter, Sir?"
							)
							((Said '//houston')
								(= goingToHouston 1)
								(agentScript changeState: 5)
							)
							((Said '//steelton')
								(= goingToSteelton 1)
								(agentScript changeState: 6)
							)
							((Said '//beirut')
								(= local11 1)
								(LocPrint 16 81) ; "After asking for a flight to Beirut, you think..."Hey, Sonny, are you out of your ever-lovin' mind??? That's no place to visit right now!""
							)
							((Said '//coarsegold')
								(= local12 1)
								(LocPrint 16 82) ; "Your ticket to Coarsegold will be $195.00, please."
								(agentScript changeState: 7)
							)
							((Said '//*')
								(LocPrint 16 83) ; "I'm sorry Sir. We don't have any flights to that location."
							)
							(else
								(event claimed: 1)
								(LocPrint 16 84) ; "Would you please be more specific, Sir?"
							)
						)
					)
					(
						(and
							(> local3 0)
							(or
								(ego inRect: 175 112 225 130)
								(ego inRect: 195 130 225 139)
								(ego inRect: 225 139 265 145)
							)
							(cond
								((Said '/houston')
									(= goingToHouston 1)
									(agentScript changeState: 5)
								)
								((Said '/steelton')
									(= goingToSteelton 1)
									(agentScript changeState: 6)
								)
								((Said '/beirut')
									(= local11 1)
									(LocPrint 16 81) ; "After asking for a flight to Beirut, you think..."Hey, Sonny, are you out of your ever-lovin' mind??? That's no place to visit right now!""
								)
								((Said '/coarsegold')
									(= local12 1)
									(LocPrint 16 82) ; "Your ticket to Coarsegold will be $195.00, please."
									(agentScript changeState: 7)
								)
								((Said '/america')
									(LocPrint 16 83) ; "I'm sorry Sir. We don't have any flights to that location."
								)
							)
						))
					(
						(or
							(Said 'ask/ticket')
							(Said 'ask/[agent,man,woman]/ticket')
						)
						(localproc_000c)
						(LocPrint 16 84) ; "Would you please be more specific, Sir?"
					)
					((Said 'pay[/agent,man,woman,ticket,flight]')
						(LocPrint 16 85) ; "With what? Good looks?"
					)
					(
						(or
							(Said 'ask,dial,use,get,pick[<up]/phone')
							(Said 'ask,make/call,phone')
						)
						(cond
							((ego inRect: 175 112 225 130)
								(localproc_000c)
								(if (Btst 38)
									(LocPrint 16 86) ; "I'm awfully busy, Officer. Would you see the next agent please?"
								else
									(LocPrint 16 87) ; "I'm sorry, Sir. The telephone is for our use only."
								)
							)
							((ego inRect: 195 130 225 139)
								(localproc_000c)
								(if (Btst 39)
									(= local1 1)
									(LocPrint 16 88) ; "Would you step around to the side of the counter, Officer?"
								else
									(LocPrint 16 87) ; "I'm sorry, Sir. The telephone is for our use only."
								)
							)
							((ego inRect: 225 139 265 145)
								(localproc_000c)
								(if (Btst 39)
									(LocPrint 16 89) ; "Certainly, Officer!"
									(egoOnThePhoneScript changeState: 0)
								else
									(LocPrint 16 87) ; "I'm sorry, Sir. The telephone is for our use only."
								)
							)
							(else
								(LocPrint 16 90) ; "There doesn't seem to be a pay phone. Perhaps one of the ticket agents will let you use their phone."
							)
						)
					)
					(
						(and
							(or
								(Said 'ask,get,give,look,see,show>')
								(Said 'have<do>')
							)
							(or
								(Said '/flight,list,passenger,info')
								(Said '//flight,list,passenger,info')
							)
						)
						(cond
							((or (== local4 0) (== local4 4) (== local4 5))
								(event claimed: 1)
								(LocPrint 16 42) ; "No one near you has any of that information."
							)
							(
								(or
									(and (== local4 1) (Btst 38))
									(and
										(or (== local4 2) (== local4 3))
										(Btst 39)
									)
								)
								(agentScript changeState: 1)
								(event claimed: 1)
							)
							(else
								(LocPrint 16 43) ; "I'm sorry sir," explains the ticket agent, "but the passenger list is confidential information."
								(event claimed: 1)
							)
						)
					)
				)
			)
		)
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(agent
					startUpd:
					setLoop: 3
					setCel: -1
					setMotion: MoveTo (Random 245 260) 110 self
				)
			)
			(1
				(agent setLoop: 4 setCel: 0 stopUpd:)
				(= local2 (Random 80 150))
			)
			(2
				(agent
					startUpd:
					setLoop: 2
					setCel: -1
					setMotion: MoveTo 230 110 self
				)
			)
			(3
				(agent stopUpd:)
				(= local2 (Random 80 150))
			)
			(4
				(self changeState: 0)
			)
			(5
				(agent
					startUpd:
					setLoop: 2
					setMotion: MoveTo 230 110 self
				)
			)
			(6
				(moveScript changeState: 9)
			)
			(7
				(agent
					startUpd:
					setLoop: (if (< (ego x?) (agent x?)) 2 else 3)
					setMotion: MoveTo (ego x?) 110 self
				)
			)
			(8
				(agent setLoop: 4 setCel: 0)
				(= local7 1)
				(self cue:)
			)
			(9
				(agent stopUpd:)
				(cond 
					((== prevRoomNum 12)
						(= local6 1)
					)
					((not local6)
						(LocPrint 16 73 83)
						(localproc_000c)
						(= local6 1)
					)
				)
			)
		)
	)
)

(instance agentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LocPrint 16 72)
			)
			(1
				(LocPrint 16 91)
				(switch local4
					(2
						(LocPrint 16 92)
					)
					(3
						(LocPrint 16 92)
					)
					(1
						(if
							(or
								(< gamePhase 6)
								(== gamePhase 13)
								(Btst fTriedToGetTicketToHouston)
							)
							(LocPrint 16 92)
						else
							(SolvePuzzle 3 fLookedAtPassengerList)
							(LocPrint 16 93)
							(LocPrint 16 94)
							(LocPrint 16 95)
							(self cue:)
						)
					)
				)
			)
			(2
				(if (!= (cSound state?) 3)
					(cSound number: 29 loop: -1 play:)
				)
				(LocPrint 16 96 83)
				(Bset fTriedToGetTicketToHouston)
				(self cue:)
			)
			(3
				(if (== currentCar 13)
					(= local8 1)
				)
			)
			(4
				(LocPrint 16 97 25 4)
				(LocPrint 16 98)
			)
			(5
				(cond 
					(
						(and
							(ego has: iPlaneTicket)
							(== airplaneToSteelton 0)
						)
						(LocPrint 16 99)
					)
					((Btst fTriedToGoToHouston)
						(LocPrint 16 100)
					)
					((Btst fHoustonAuthorized)
						(if (not (ego has: iPlaneTicket))
							(LocPrint 16 101)
							(LocPrint 16 102)
						else
							(LocPrint 16 103)
						)
						(= goingToHouston 0)
						(ego get: iPlaneTicket)
						(= airplaneToSteelton 0)
						(Bclr fHoustonAuthorized)
						(HandsOn)
					)
					(else
						(LocPrint 16 104)
						(LocPrint 16 105)
						(HandsOn)
						(if
							(and
								(cast contains: keith)
								(not (Btst fHoustonAuthorized))
								local13
							)
							(keith setScript: getTheTicketScript)
						)
					)
				)
			)
			(6
				(cond 
					((== airplaneToSteelton 1)
						(LocPrint 16 106)
					)
					((Btst fSteeltonAuthorized)
						(if (not (ego has: iPlaneTicket))
							(LocPrint 16 107)
							(LocPrint 16 102)
							(if (ego has: iFieldKit)
								(LocPrint 16 108)
							)
						else
							(LocPrint 16 109)
						)
						(= goingToSteelton 0)
						(SolvePuzzle 3 fGotTicketToSteelton)
						(ego get: iPlaneTicket)
						(= airplaneToSteelton 1)
						(if (IsObject theFieldKit)
							(theFieldKit dispose:)
							(= fieldKitOpen 0)
						)
						(ego put: iFieldKit 16)
						(HandsOn)
					)
					(else
						(LocPrint 16 110)
						(LocPrint 16 111)
						(HandsOn)
						(if
							(and
								(cast contains: keith)
								(not (Btst fSteeltonAuthorized))
								local14
							)
							(keith setScript: getTheTicketScript)
						)
					)
				)
			)
			(7
				(LocPrint 16 112)
			)
		)
	)
)

(instance keithJoinsEgoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(keith
					posn: 165 209
					setMotion: MoveTo 168 180 self
				)
			)
			(1
				(LocPrint 16 113 83 25 3)
				(keith setMotion: MoveTo 168 151 self)
			)
			(2
				(keith
					loop: 0
					cel: 2
				)
				(if (< (ego y?) 115)
					(ego setMotion: MoveTo 155 114 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo (+ (keith x?) 20) (keith y?) self)
			)
			(4
				(ego loop: 1)
				(switch global169
					(1
						(= global169 3)
						(LocPrint 16 114 83)
						(LocPrint 16 115)
						(LocPrint 16 116)
					)
					(2
						(LocPrint 16 117 83)
						(LocPrint 16 116)
					)
					(3 (LocPrint 16 118 83))
				)
				(= global169 0)
				(self cue:)
			)
			(5
				(LocPrint 16 119 83)
				(LocPrint 16 120)
				(keith setMotion: Follow ego 25)
				(HandsOn)
			)
		)
	)
)

(instance getTheTicketScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LocPrint 16 121)
				(= local15 1)
				(keith setMotion: 0)
				(self cue:)
			)
			(1
				(if (< (keith y?) (+ (ego y?) 5))
					(keith setMotion: MoveTo (- (ego x?) 20) (+ (ego y?) 5) self)
				else
					(self cue:)
				)
			)
			(2
				(keith setMotion: MoveTo 258 142 self)
			)
			(3
				(keith loop: 3)
				(agent
					setLoop: 3
					setCel: 0
					setMotion: MoveTo 244 119 self
					illegalBits: 0
				)
			)
			(4
				(LocPrint 16 122 25 7 83)
				(self cue:)
			)
			(5
				(keith hide:)
				(agent setCel: -1 hide:)
				((= keithCallingCaptain (Prop new:))
					view: 667
					loop: 0
					posn: 253 138
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(6
				(LocPrint 16 123 25 10 83)
				(self cue:)
			)
			(7
				(keithCallingCaptain setCycle: BegLoop self)
			)
			(8
				(keithCallingCaptain dispose:)
				(keith show:)
				(agent show:)
				(self cue:)
			)
			(9
				(agent setMotion: MoveTo 244 110)
				(keith setMotion: Follow ego 25)
				(if (or local14 local13)
					(LocPrint 16 124 83)
					(cond 
						(goingToHouston
							(Bset fHoustonAuthorized)
							(= goingToHouston 0)
						)
						(goingToSteelton
							(Bset fSteeltonAuthorized)
							(= goingToSteelton 0)
							(if
								(not (Btst fColbyPhoneTap))
								(LocPrint 16 125)
							)
						)
					)
				else
					(LocPrint 16 126)
					(LocPrint 16 127)
				)
				(HandsOn)
				(self cue:)
			)
			(10
				(= local15 0)
				(moveScript changeState: 2)
			)
		)
	)
)

(instance egoOnThePhoneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fKeithFollows)
					(if (keith inRect: 225 139 280 145)
						(HandsOff)
						(keith
							setMotion: MoveTo
								(if (> (ego x?) (keith x?))
									210
								else
									290
								) 
								(keith y?) self
						)
					else
						(self cue:)
					)
				else
					(self cue:)
				)
			)
			(1
				(HandsOn)
				(ego setMotion: MoveTo 258 139 self)
			)
			(2
				(= local1 0)
				(ego loop: 3)
				(agent
					setLoop: 3
					setCel: 0
					setMotion: MoveTo 244 119 self
					illegalBits: 0
				)
			)
			(3
				(LocPrint 16 128 25 3 83)
				(self cue:)
			)
			(4
				(HandsOff)
				(agent setCel: -1 hide:)
				(ego
					view: 667
					loop: 1
					posn: 253 139
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				(= perspective 0)
				(curRoom newRoom: 12)
			)
			(6
				(ego setCycle: BegLoop self)
			)
			(7
				(HandsOn)
				(NormalEgo)
				(ego view: 1 loop: 1 posn: 260 139)
				(agent setLoop: 3 setCel: 0 posn: 244 119)
			)
		)
	)
)

(instance pictureOnWall of PicView
	(properties
		y 88
		x 44
		view 77
		loop 7
		cel 2
		priority 8
		signal $4000
	)
)

(instance sittingPerson of PicView
	(properties
		y 125
		x 55
		view 77
		loop 5
		priority 8
		signal $4000
	)
)

(instance counterPerson of PicView
	(properties
		y 124
		x 179
		view 77
		loop 5
		cel 1
		priority 8
	)
)

(instance talking1 of PicView
	(properties
		y 150
		x 78
		view 77
		loop 5
		cel 2
		priority 11
	)
)

(instance talking2 of PicView
	(properties
		y 151
		x 101
		view 77
		loop 5
		cel 3
		priority 11
	)
)

(instance talking3 of PicView
	(properties
		y 153
		x 91
		view 77
		loop 5
		cel 4
		priority 11
	)
)

(instance standing1 of PicView
	(properties
		y 98
		x 145
		view 77
		loop 6
		cel 4
		priority 5
	)
)

(instance standing2 of PicView
	(properties
		y 103
		x 240
		view 77
		loop 6
		cel 1
		priority 5
		signal $4000
	)
)

(instance standing3 of PicView
	(properties
		y 104
		x 171
		view 77
		loop 6
		cel 2
		priority 5
	)
)

(instance standing4 of PicView
	(properties
		y 106
		x 164
		view 77
		loop 6
		priority 5
	)
)

(instance standing5 of PicView
	(properties
		y 100
		x 154
		view 77
		loop 6
		cel 3
		priority 5
	)
)

(instance case of PicView
	(properties
		y 129
		x 61
		view 77
		loop 7
		cel 1
		priority 9
	)
)

(instance SIGN of PicView
	(properties
		y 70
		x 29
		view 77
		loop 7
		priority 9
	)
)

(instance poster1 of PicView
	(properties
		y 117
		x 190
		view 77
		loop 7
		cel 3
		priority 8
		signal $4000
	)
)

(instance poster2 of PicView
	(properties
		y 126
		x 212
		view 77
		loop 7
		cel 4
		priority 9
		signal $4000
	)
)

(instance agent2 of PicView
	(properties
		y 103
		x 211
		view 77
		loop 4
		cel 1
		priority 8
	)
)
