;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm20 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	guardShownBadge
	local10
	local11
	newProp
	local13
	local14
	local15
	local16
	local17
	local18
)
(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(instance larrySound of Sound
	(properties
		number 28
	)
)

(instance theBlonde of Sound
	(properties
		number 21
	)
)

(instance larry of Actor
	(properties
		view 81
		loop 3
		cycleSpeed 2
	)
	
	(method (init)
		(larry posn: 231 124 setPri: 1)
		(if (< howFast 30)
			(larry stopUpd:)
		else
			(larry setScript: larryActions)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
				)
				(return)
			)
			(
				(not
					(ego inRect: 222 124 265 135)
				)
				(if (Said '/jerk')
					(NotClose)
				)
				(return)
			)
			((Said 'look/dude,jerk')
				(LocPrint 20 0)
			)
			((Said 'frisk,arrest,arrest/dude,jerk')
				(LocPrint 20 1)
			)
			((Said 'display,flash/badge')
				(if (ego has: iWallet)
					(LocPrint 20 2)
				else
					(LocPrint 20 3)
				)
			)
			((Said 'chat/dude,jerk')
				(= local16 0)
				(switch local4
					(0
						(larrySound play:)
						(LocPrint 20 4)
					)
					(1
						(LocPrint 20 5)
						(= local16 1)
					)
					(2
						(LocPrint 20 6)
					)
					(3
						(LocPrint 20 7)
					)
					(4
						(LocPrint 20 8)
						(= local16 2)
					)
					(5
						(LocPrint 20 9)
						(LocPrint 20 10)
						(LocPrint 20 11)
					)
				)
				(if (< local4 5)
					(++ local4)
				else
					(= local4 0)
				)
			)
			((Said 'affirmative')
				(switch local16
					(1
						(LocPrint 20 12)
					)
					(2
						(LocPrint 20 13)
					)
					(else 
						(event claimed: 0)
					)
				)
				(= local16 0)
			)
			((Said 'no')
				(switch local16
					(1
						(LocPrint 20 14)
					)
					(2
						(LocPrint 20 15)
					)
					(else
						(event claimed: 0)
					)
				)
				(= local16 0)
			)
		)
	)
)

(instance larryActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 20 40))
			)
			(1
				(switch (Random 0 1)
					(0
						(larry
							setLoop: 4
							setCycle: EndLoop
						)
					)
					(1
						(larry
							setLoop: 3
							setCycle: EndLoop
						)
					)
				)
				(= cycles (Random 40 60))
			)
			(2
				(larry setCycle: BegLoop)
				(self changeState: 0)
			)
		)
	)
)

(instance guard of Actor
	(properties)
)

(instance manStanding of Prop
	(properties)
)

(instance rm20 of Room
	(properties
		picture 20
		style DISSOLVE
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 0)
		(Load VIEW 77)
		(Load VIEW 31)
		(Load VIEW 76)
		(super init:)
		(= perspective 70)
		(= gunFireState gunPROHIBITED)
		(= gunNotNeeded 1)
		(User canInput: 1)
		(if
			(or
				(not (Btst fKeithFollows))
				(!= prevRoomNum 40)
			)
			(= local2 1)
		)
		(if
			(and
				(ego has: iPlaneTicket)
				(Btst fKeithFollows)
			)
			(= local2 2)
		)
		(if (== prevRoomNum 40)
			(= local2 3)
			(Bset fKeithFollows)
		)
		(if
			(and
				(== prevRoomNum 17)
				(not (ego has: iPlaneTicket))
			)
			(= local2 0)
		)
		(guard
			view: 31
			posn: 128 133
			loop: 0
			cel: 1
			init:
			brTop: 131
			brBottom: 133
		)
		((View new:)
			view: 81
			posn: 208 96
			loop: 0
			cel: 0
			setPri: 1
			init:
			addToPic:
		)
		((View new:)
			view: 81
			posn: 188 125
			loop: 2
			cel: 0
			setPri: 1
			init:
			addToPic:
		)
		((View new:)
			view: 81
			posn: 211 125
			loop: 2
			cel: 1
			setPri: 1
			init:
			addToPic:
		)
		(manStanding
			view: 81
			posn: 92 140
			loop: 1
			cel: 0
			init:
			stopUpd:
		)
		(larry init:)
		(self setLocales: regFieldKit)
		(self setScript: rm20Script)
	)
	
	(method (dispose)
		(manScript dispose:)
		(keithScript dispose:)
		(guardScript dispose:)
		(features eachElementDo: #dispose #delete)
		(super dispose:)
	)
)

(instance rm20Script of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(< (ego x?) 12)
				(not local5)
			)
			(switch local2
				(0
					(LocPrint 20 16)
					(ego setMotion: MoveTo 110 124)
				)
				(1
					(LocPrint 20 17)
					(ego setMotion: MoveTo 110 124)
				)
				(2
					(if (not local14)
						(= local14 1)
						(boardingScript changeState: 0)
					)
				)
				(3
					(boardingScript changeState: 5)
				)
			)
		)
		(cond 
			(
				(and
					(not (cast contains: keith))
					(Btst fKeithFollows)
					local8
				)
				(keithScript changeState: 0)
			)
			(
				(and
					(== (ego onControl: 1) cLRED)
					(not local17)
				)
				(= local17 1)
				(if (!= prevRoomNum 17)
					(self changeState: 4)
				else
					(self changeState: 5)
				)
			)
		)
		(cond 
			((> local0 1)
				(-- local0)
			)
			((== local0 1)
				(= local0 0)
				(manScript cue:)
			)
		)
		(if
			(and
				(> (ego y?) 127)
				(> (ego x?) 104)
			)
			(= local5 1)
		else
			(= local5 0)
		)
		(cond 
			(
				(and
					(not local7)
					local8
					(== (ego onControl: 1) cYELLOW)
				)
				(LocPrint 20 18)
				(= local7 1)
			)
			(
				(and
					local7
					(!= (ego onControl: 1) cYELLOW)
				)
				(= local7 0))
		)
		(cond 
			((<= (ego y?) 140)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(if
			(and
				(== (ego onControl: 1) cLMAGENTA)
				(== (ego loop?) 3)
				(not local6)
				(or
					guardShownBadge
					(!= prevRoomNum 17)
					(not (ego has: 0))
				)
			)
			(= local6 1)
			(if
				(and
					(> (++ local18) 2)
					(== (mod local18 2) 0)
				)
				(LocPrint 20 19)
			)
		)
		(if
			(and
				local6
				(!= (ego onControl: 1) cLMAGENTA)
			)
			(= local6 0)
		)
		(if
			(and
				(== (ego onControl: 1) cLMAGENTA)
				(== (ego loop?) 3)
				(not guardShownBadge)
				(== prevRoomNum 17)
			)
			(cond 
				((not local10)
					(guardScript changeState: 3)
				)
				(local11
					(cond 
						((ego has: 0)
							(guardScript changeState: 10)
						)
						((< local18 3)
							(LocPrint 20 20)
						)
					)
				)
			)
		)
		(if
			local13
			(guardScript changeState: 18)
		)
		(if
			(and
				gunDrawn
				(not local15)
				(ego inRect: 105 132 168 168)
			)
			(guardScript changeState: 21)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo)
				(if (== prevRoomNum 17)
					(HandsOff)
					(ego
						view: 1
						posn: 197 210
						init:
						setLoop: 2
						setCel: 1
						setCycle: 0
						setPri: 11
						illegalBits: 0
						setMotion: MoveTo 208 161 self
					)
				else
					(ego
						view: 1
						posn: 15 124
						init:
						setMotion: MoveTo 153 124
					)
					((= keith (Actor new:))
						view: 20
						setCycle: Walk
						posn: 24 124
						init:
					)
					(= local8 1)
					(keithScript changeState: 8)
					(Bset fKeithFollows)
				)
			)
			(1
				(ego
					setLoop: -1
					setCel: -1
					setPri: -1
					setCycle: Walk
					xStep: 3
					yStep: 2
					setMotion: MoveTo 210 169
					startUpd:
				)
				(NormalEgo)
				(if (Btst fKeithFollows)
					(self cue:)
				else
					(HandsOn)
				)
			)
			(2
				(ego setMotion: MoveTo 238 167 self)
			)
			(3
				(ego
					illegalBits: 0
					setLoop: 1
				)
				(= local8 1)
			)
			(4
				(ego setMotion: 0)
				(LocPrint 20 21 83)
				(keith
					ignoreActors: 0
					setLoop: -1
					setMotion: MoveTo 158 133 self
				)
			)
			(5
				(HandsOff)
				(if (cast contains: keith)
					(keith setMotion: MoveTo 158 170)
				)
				(ego
					setLoop: 3
					setCel: 0
					setPri: 11
					setMotion: MoveTo (- (ego x?) 20) 204 self
					init:
					ignoreActors:
					illegalBits: 0
				)
			)
			(6
				(if (cast contains: keith)
					(if (== prevRoomNum 17)
						(keithScript changeState: 4)
					else
						(keith setMotion: MoveTo 250 170)
						(= perspective 0)
						(curRoom newRoom: 17)
					)
				else
					(= perspective 0)
					(curRoom newRoom: 17)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/noword,chamber,gate]')
								(if local5
									(LocPrint 20 22)
								else
									(LocPrint 20 23)
								)
							)
							((Said '/pane')
								(if (< (ego x?) 150)
									(LocPrint 20 24)
								else
									(LocPrint 20 25)
								)
							)
							(
								(or
									(Said '<up')
									(Said '/ceiling')
								)
								(LocPrint 20 26)
							)
							(
								(or
									(Said '<down')
									(Said '/floor')
								)
								(LocPrint 20 27)
							)
							((Said '/bench')
								(if local5
									(LocPrint 20 28)
								else
									(LocPrint 20 29)
								)
							)
							((Said '/painting')
								(if
									(and
										(< (ego y?) 140)
										(> (ego x?) 140)
										local5
									)
									(LocPrint 20 30)
								else
									(LocPrint 20 31)
								)
							)
							((Said '/rope')
								(if
									(and
										(< (ego x?) 150)
										local5
									)
									(LocPrint 20 32)
								else
									(LocPrint 20 33)
								)
							)
							((Said '/wall')
								(LocPrint 20 34)
							)
							((Said '/guard')
								(LocPrint 20 35)
							)
							((Said '/escalator')
								(if local5
									(LocPrint 20 36)
								else
									(LocPrint 20 37)
								)
							)
							((Said '/broad,broad,blonde')
								(if (<= (ego y?) 137)
									(LocPrint 20 38)
								else
									(LocPrint 20 39)
								)
							)
							((Said '/men,crowd,passenger')
								(LocPrint 20 40)
							)
							((Said '/dude,person')
								(cond 
									((ego inRect: 160 124 200 135)
										(LocPrint 20 41)
									)
									((ego inRect: 200 124 222 135)
										(LocPrint 20 42)
									)
									((ego inRect: 108 128 163 147)
										(LocPrint 20 35)
									)
									((ego inRect: 83 128 115 136)
										(LocPrint 20 43)
									)
									(else
										(LocPrint 20 44)
									)
								)
							)
						)
					)
					((Said 'sat[<down][/bench]')
						(LocPrint 20 45)
					)
					((Said 'move,get/painting')
						(LocPrint 20 46)
					)
					((Said 'move,remove,break,cut,hoist,get/rope')
						(if
							(and
								(< (ego x?) 150)
								local5
							)
							(LocPrint 20 47)
						else
							(LocPrint 20 33)
						)
					)
					((Said 'display,flash/badge')
						(if (ego has: iWallet)
							(if local5
								(cond 
									((ego inRect: 222 124 265 135)
										(LocPrint 20 2)
									)
									((ego inRect: 163 124 221 135)
										(switch (Random 0 3)
											(0
												(LocPrint 20 48)
											)
											(1
												(LocPrint 20 49)
											)
											(2
												(LocPrint 20 50)
											)
											(3
												(LocPrint 20 51)
											)
										)
									)
									((ego inRect: 83 128 115 136)
										(LocPrint 20 52)
									)
									((cast contains: newProp)
										(= guardShownBadge 1)
										(SolvePuzzle 2 fGateGuardShownBadge)
									)
									((ego inRect: 108 128 163 147)
										(if (not guardShownBadge)
											(= guardShownBadge 1)
											(SolvePuzzle 2 fGateGuardShownBadge)
											(= local10 1)
											(guardScript changeState: 0)
										else
											(LocPrint 20 53)
										)
									)
									(else (LocPrint 20 54))
								)
							else
								(LocPrint 20 54)
							)
						else
							(LocPrint 20 3)
						)
					)
					((Said 'display/mugshot,painting,[shot<mug]')
						(if
							(and
								(not (ego has: iNewMugShot))
								(not (ego has: iOldMugShot))
							)
							(LocPrint 20 55)
						else
							(LocPrint 20 56)
						)
					)
					(
						(or
							(Said 'draw,pull,use,display/9mm')
							(Said 'fire,kill')
						)
						(cond 
							((not (ego has: iHandGun))
								(DontHaveGun)
							)
							((not (ego inRect: 108 128 163 147))
								(LocPrint 20 57)
							)
							(
								(and
									(>= (guardScript state?) 14)
									(<= (guardScript state?) 17)
								)
								(LocPrint 20 58)
							)
							(else
								(LocPrint 20 59 25 4)
								(guardScript changeState: 21)
							)
						)
					)
					((Said 'chat>')
						(cond 
							((Said '/broad,broad,blonde')
								(if (ego inRect: 60 140 120 165)
									(if
										(not local3)
										(manScript changeState: 0)
									)
								else
									(LocPrint 20 38)
								)
							)
							((Said '/guard,cop')
								(if (ego inRect: 108 128 163 147)
									(LocPrint 20 60)
								else
									(LocPrint 20 61)
								)
							)
							((Said '/dude')
								(cond 
									((ego inRect: 160 124 200 135)
										(LocPrint 20 62)
									)
									((ego inRect: 200 124 222 135)
										(LocPrint 20 63)
									)
									((ego inRect: 83 128 115 136)
										(LocPrint 20 64)
									)					
									((ego inRect: 60 140 120 165)
										(LocPrint 20 65)
									)
									((ego inRect: 108 128 163 147)
										(LocPrint 20 66)
									)
									(else
										(LocPrint 20 67)
									)
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance manScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theBlonde init: play:)
				(manStanding cel: 1 forceUpd:)
				(= local3 1)
				(= local0 20)
			)
			(1
				(LocPrint 20 68)
				(= local0 20)
			)
			(2
				(manStanding cel: 0 forceUpd:)
				(= local3 0)
			)
		)
	)
)

(instance keithScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= keith (Actor new:))
					view: 20
					setCycle: Forward
					setLoop: 2
					setCel: 1
					xStep: 3
					yStep: 2
					ignoreActors: 0
					posn: 206 204
					setPri: 11
					setMotion: MoveTo 204 161 self
					init:
					illegalBits: 0
				)
			)
			(1
				(keith
					setPri: -1
					setLoop: -1
					setCel: -1
					setCycle: Walk
					ignoreActors: 0
					illegalBits: cWHITE ;-24576
					setMotion: MoveTo 204 164 self
				)
				(ego
					illegalBits: cWHITE ;-32768
					setLoop: -1
				)
			)
			(2
				(HandsOn)
				(keith setMotion: MoveTo 111 168 self)
			)
			(3
				(keith loop: 0)
			)
			(4
				(keith
					ignoreActors: 0
					setPri: -1
					setMotion: MoveTo 260 165 self
				)
			)
			(5
				(keith setMotion: MoveTo 260 162 self)
			)
			(6
				(keith
					setLoop: 3
					setCel: 0
					setPri: 11
					setMotion: MoveTo (- (keith x?) 20) 204 self
					init:
					ignoreActors:
					illegalBits: 0
				)
			)
			(7
				(= perspective 0)
				(curRoom newRoom: 17)
			)
			(8
				(keith setMotion: MoveTo 132 124 self)
			)
			(9
				(keith setMotion: MoveTo 150 132 self)
			)
			(10
				(keith setMotion: MoveTo 198 134 self)
			)
			(11
				(keith loop: 1 cel: 1)
			)
		)
	)
)

(instance guardScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: 0)
				(guard
					ignoreActors: 0
					setMotion: MoveTo 128 133 self
				)
			)
			(1
				(guard loop: 2 cel: 1)
				(RedrawCast)
				(self cue:)
			)
			(2
				(LocPrint 20 69)
				(guard loop: 0)
				(RedrawCast)
				(User canControl: 1)
			)
			(3
				(= local10 1)
				(HandsOff)
				(ego setMotion: 0)
				(if (ego has: 0)
					(LocPrint 20 70 25 4)
				else
					(LocPrint 20 71 25 4)
				)
				(guard
					setCycle: Walk
					setMotion: MoveTo 140 133 self
				)
			)
			(4
				(guard setMotion: MoveTo 149 133 self)
			)
			(5
				(guard
					loop: 2
					cel: 4
				)
				(self cue:)
			)
			(6
				(LocPrint 20 72 25 5 83)
				(ego setMotion: MoveTo 155 144 self)
			)
			(7
				(ego setMotion: MoveTo 138 144)
				(guard setMotion: MoveTo 128 133 self)
			)
			(8
				(ego cel: 6)
				(guard
					loop: 0
					cel: 1
				)
				(self cue:)
			)
			(9
				(HandsOn)
				(= local11 1)
				(LocPrint 20 73 25 4 83)
			)
			(10
				(HandsOff)
				(= local11 0)
				(User canControl: 0)
				(LocPrint 20 74 25 4)
				(LocPrint 20 75 25 4)
				(self cue:)
			)
			(11
				(ego
					illegalBits: 0
					setMotion: MoveTo 162 134 self
				)
			)
			(12
				(ego
					illegalBits: cWHITE ;-32768
					loop: 1
					cel: 7
				)
				(guard
					loop: 6
					cel: 0
					setCycle: EndLoop self
				)
			)
			(13
				(LocPrint 20 76 25 4 83)
				(self cue:)
			)
			(14
				(guard
					setLoop: 7
					ignoreActors:
					setCycle: Walk
					setMotion: MoveTo 153 133 self
				)
			)
			(15
				(guard cel: 0)
				(self cue:)
			)
			(16
				(ego hide:)
				(guard hide:)
				((= newProp (Prop new:))
					view: 31
					loop: 8
					cel: 0
					posn: 157 134
					init:
					startUpd:
					cycleSpeed: 3
					setCycle: EndLoop self
				)
				(User canInput: 1)
			)
			(17
				(LocPrint 20 77 25 4 83)
				(if guardShownBadge
					(LocPrint 20 78)
					(HandsOn)
					(= local13 1)
				else
					(LocPrint 20 79)
					(EgoDead
						{As you have just discovered, you have to use your head if you're a cop. You didn't, and now this guy's going to arrest YOU!}
					)
				)
			)
			(18
				(= local13 0)
				(newProp dispose:)
				(guard
					ignoreActors: 0 
					show:
				)
				(ego
					illegalBits: cWHITE ;-32768
					posn: 162 134
					show:
				)
				(self cue:)
			)
			(19
				(guard
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 128 133 self
				)
			)
			(20
				(guard setLoop: 0)
				(User canControl: 1)
				(LocPrint 20 80 25 4 83)
			)
			(21
				(= local15 1)
				(guard
					setCycle: Walk
					setMotion: MoveTo 151 133 self
				)
			)
			(22
				(guard
					setLoop: 4
					setCycle: EndLoop self
				)
			)
			(23
				(guard
					setLoop: 5
					setCycle: EndLoop self
				)
			)
			(24
				(ego hide:)
				((View new:)
					view: 76
					loop: 7
					cel: 0
					posn: 147 155
					init:
					ignoreActors:
					addToPic:
				)
				(Print 20 81 #at -1 116 #draw)
				(self cue:)
			)
			(25
				(EgoDead
					{Nobody ever figured out why Sonny Bonds tried to draw down on an airport security guard, but the results were obviously disastrous.}
				)
			)
		)
	)
)

(instance boardingScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LocPrint 20 82 25 5 83)
				(keith
					illegalBits: 0
					ignoreActors:
					startUpd:
					setLoop: 0
					setMotion: MoveTo 155 139 self
				)
				(ego put: iPlaneTicket 16)
			)
			(1
				(LocPrint 20 83 83)
				(keith setLoop: 1)
				(self cue:)
			)
			(2
				(LocPrint 20 84 83)
				(keith
					setLoop: 3
					setMotion: MoveTo 155 124 self
				)
			)
			(3
				(keith
					setLoop: 1
					setMotion: MoveTo -5 124 self
				)
			)
			(4
				(HandsOn)
				(if (== airplaneToSteelton 1)
					(= gamePhase 14)
				else
					(Bset fTriedToGoToHouston)
				)
				(= perspective 0)
				(curRoom newRoom: 40)
			)
			(5
				(LocPrint 20 85)
				(ego setMotion: MoveTo 110 124)
			)
		)
	)
)
