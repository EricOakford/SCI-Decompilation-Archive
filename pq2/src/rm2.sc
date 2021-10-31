;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm2 0
)
(synonyms
	(dude cop)
)

(local
	bigJon
	mrG
	burglaryDoor
	lockerRoomDoor
	narcoticsDoor
	homicideDoor
	local6
	bookedEvidence
	local8
)
(procedure (LocPrint)
	(Print &rest #at -1 30)
)

(procedure (GiveBlood)
	(if (ego has: iVialOfBlood)
		(if (Btst fHaveCoveBlood) ;144
			(Bclr fHaveCoveBlood)
			(ProcessEvidence iVialOfBlood 146 2 87)
			(ego get: iVialOfBlood)
		)
		(if (Btst fHaveTrunkBlood) ;143
			(Bclr fHaveTrunkBlood)
			(ProcessEvidence iVialOfBlood 147 2 88)
			(ego get: iVialOfBlood)
		)
		(if (Btst fHaveMotelBlood) ;113
			(Bclr fHaveMotelBlood)
			(ProcessEvidence iVialOfBlood 148 2 89)
			(ego get: iVialOfBlood)
		)
		(if (ego has: iVialOfBlood)
			(PutInRoom iVialOfBlood)
		)
	)
)

(procedure (ProcessEvidence invItem flag)
	(return
		(if (ego has: invItem)
			(if bookedEvidence
				(Bset fGotPoints) ;125
			)
			(= bookedEvidence 1) ;is this seems wrong, test it
			(switch invItem
				(13
					(if (Btst fBookedColbyCard)
						(= gamePhase 13)
					)
				)
				(35
					(if (Btst fBookedHitList)
						(= gamePhase 13)
					)
				)
			)
			(SolvePuzzle 1)
			(Bset flag)
			(ego put: invItem 2)
			(LocPrint &rest)
			(return 1)
		else
			(return 0)
		)
	)
)

(instance rm2 of Room
	(properties
		picture 2
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(Load VIEW 1)
		(Load VIEW 0)
		(Load VIEW 56)
		(Load VIEW 57)
		(self setLocales: 153)
		(HandsOn)
		(= gunFireState gunPROHIBITED)
		((View new:)
			view: 56
			loop: 3
			cel: 0
			posn: 291 97
			setPri: 9
			init:
			addToPic:
		)
		((= burglaryDoor (AutoDoor new:))
			doorControl: 4096
			entranceTo: 6
			facingLoop: 3
			view: 56
			loop: 2
			posn: 246 103
			setPri: 6
			init:
			stopUpd:
		)
		((= homicideDoor (AutoDoor new:))
			doorControl: 8192
			entranceTo: 4
			facingLoop: 1
			view: 56
			loop: 0
			posn: 77 113
			setPri: 6
			init:
			stopUpd:
		)
		((= lockerRoomDoor (AutoDoor new:))
			doorControl: 2048
			entranceTo: 5
			facingLoop: 3
			view: 56
			loop: 2
			posn: 122 105
			setPri: 2
			init:
			stopUpd:
		)
		((= narcoticsDoor (AutoDoor new:))
			doorControl: 16384
			entranceTo: 3
			facingLoop: 1
			view: 56
			loop: 1
			posn: 46 121
			setPri: 7
			init:
			stopUpd:
		)
		((= bigJon (Actor new:))
			view: 57
			illegalBits: 0
			posn: 212 93
			setPri: 5
			setCycle: Walk
			cycleSpeed: 2
			init:
			setScript: bigJonScript
		)
		(if
			(and
				(< gamePhase 6)
				(or
					(and (== prevRoomNum 1) (Btst fDocBookingEvidence))
					(and (!= prevRoomNum 1) (== (Random 0 2) 1))
				)
			)
			(Bset fDocBookingEvidence)
			((= mrG (Actor new:))
				view: 48
				posn: 191 109
				loop: 3
				cel: 4
				setCycle: Walk
				setMotion: 0
				init:
				stopUpd:
				setScript: mrGScript
			)
			(bigJon
				posn: 182 93 
				loop: 2
			)
			(bigJonScript changeState: 1)
		else
			(Bclr 10)
		)
		(self setScript: rm2Script)
	)
	
	(method (dispose)
		(bigJonScript dispose:)
		(mrGScript dispose:)
		(DisposeScript 301)
		(super dispose:)
	)
)

(instance rm2Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((== (burglaryDoor doorState?) 2)
				(ego heading: 0 setMotion: MoveTo 180 10)
				(curRoom newRoom: 6)
			)
			((== (homicideDoor doorState?) 2)
				(ego heading: 0 setMotion: MoveTo 180 10)
				(curRoom newRoom: 4)
			)
			((== (lockerRoomDoor doorState?) 2)
				(ego heading: 0 setMotion: MoveTo 180 10)
				(curRoom newRoom: 5)
			)
			((== (narcoticsDoor doorState?) 2)
				(ego heading: 0 setMotion: MoveTo 180 10)
				(curRoom newRoom: 3)
			)
			((>= (ego y?) 165) (= global160 1)
				(curRoom newRoom: 1)
			)
			((ego inRect: 272 106 290 125)
				(curRoom newRoom: 10)
			)
			((<= (ego y?) 126)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: (if (not gunDrawn) 1 else 7) init:)
				(switch prevRoomNum
					(3
						(ego
							posn: 63 117
							heading: 90
							setMotion: MoveTo 400 117
						)
						(User prevDir: 3)
					)
					(4
						(ego
							view: (if (not gunDrawn) 0 else 6)
							posn: 87 111
							heading: 90
							setMotion: MoveTo 400 111
						)
						(User prevDir: 3)
					)
					(5
						(ego
							view: (if (not gunDrawn) 0 else 6)
							posn: 120 114
							heading: 180
							setMotion: MoveTo 120 300
						)
						(User prevDir: 5)
					)
					(6
						(ego
							posn: 246 111
							heading: 180
							setMotion: MoveTo 246 300
						)
						(User prevDir: 5)
					)
					(10
						(ego
							view: (if (not gunDrawn) 0 else 6)
							posn: 270 112
							heading: 270
							setMotion: MoveTo 0 112
						)
						(User prevDir: 7)
					)
					(1
						(ego
							posn: 162 162
							heading: 180
							setMotion: MoveTo 162 10
						)
						(User prevDir: 1)
					)
					(else 
						(ego
							posn: 160 140
							setMotion: 0
						)
					)
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
							((Said '<in/bin,locker')
								(cond 
									((not (ego inRect: 250 105 280 122))
										(NotClose)
									)
									((not (Btst 11))
										(Print 2 0)
									)
									((InRoom 10)
										(Print 2 1)
									)
									(else
										(Print 2 2)
									)
								)
							)
							((Said '/door')
								(cond 
									(
										(and
											(ego inRect: 110 108 142 155)
											(== (ego loop?) 3)
										)
											(Print 2 3)
									)
									(
										(or
											(and (ego inRect: 42 117 200 130) (== (ego loop?) 1))
											(and (ego inRect: 30 0 65 200) (== (ego loop?) 3))
										)
										(Print 2 4)
									)
									(
										(or
											(and (ego inRect: 70 108 200 118) (== (ego loop?) 1))
											(and (ego inRect: 64 0 90 200) (== (ego loop?) 3))
										)
										(Print 2 5)
									)
									(
										(and
											(ego inRect: 223 105 280 130)
											(== (ego loop?) 3)
										)
										(Print 2 6)
									)
									(else
										(Print 2 7)
									)
								)
							)
							((Said '/counter,bin,locker')
								(cond 
									((ego inRect: 250 105 280 122)
										(Print 2 8)
									)
									((< (ego x?) 172)
										(Print 2 9)
									)
									((> (ego y?) 122)
										(Print 2 10)
									)
									(else
										(Print 2 8)
									)
								)
							)
							(
								(and
									(cast contains: mrG)
									(Said '/gelepsi,dude')
								)
									(Print 2 11)
								)
							((Said 'look/dude,john')
								(if
									(and
										(not (cast contains: mrG))
										(<= (bigJonScript state?) 1)
									)
										(Print 2 12)
								else
									(Print 2 13)
								)
							)
							((Said '/pane')
								(Print 2 14)
							)
							((Said '/wall')
								(Print 2 15)
							)
							((Said '/painting')
								(Print 2 16)
							)
							((Said '[<around,at][/(noword,chamber,hall)]')
								(Print 2 17)
							)
						)
					)
					((Said 'unlock/door')
						(Print 2 18)
					)
					((Said 'close/door')
						(Print 2 19)
					)
					((Said 'open,unlock/bin,locker')
						(cond 
							((not (ego inRect: 250 105 280 122))
								(NotClose)
							)
							((Btst fKitBinOpen)
								(Print 2 20)
							)
							((not (ego has: iKeyRing))
								(Print 2 21)
							)
							((InRoom iFieldKit)
								(Bset fKitBinOpen)
								(Print 2 22)
							)
							(else
								(Bset fKitBinOpen)
								(Print 2 23)
							)
						)
					)
					((Said 'close,lock/bin,locker')
						(cond 
							((not (ego inRect: 250 105 280 122))
								(NotClose)
							)
							((not (Btst fKitBinOpen))
								(Print 2 24)
							)
							((ego has: 2)
								(Print 2 25)
								(Bclr fKitBinOpen)
							)
							(else
								(Print 2 21)
							)
						)
					)
					((Said 'get/briefcase')
						(cond 
							((not (ego inRect: 250 105 280 122))
								(NotClose)
							)
							((not (Btst fKitBinOpen))
								(Print 2 26)
							)
							((ego has: iFieldKit)
								(AlreadyTook)
							)
							((not (InRoom 10))
								(Print 2 27)
							)
							(else
								(Print 2 28)
								(ego get: iFieldKit)
								(SolvePuzzle 2 fGetFieldKit)
							)
						)
					)
					((Said 'deposit/briefcase')
						(cond 
							((not (ego inRect: 250 105 280 122))
								(event claimed: 1)
								(NotClose)
							)
							((not (Btst fKitBinOpen))
								(event claimed: 1)
								(Print 2 26)
							)
							((ego has: iFieldKit)
								(Print 2 29)
								(if (IsObject theFieldKit)
									(theFieldKit dispose:)
								)
								(PutInRoom iFieldKit)
							)
							(else
								(Print 2 30)
							)
						)
					)
				)
			)
		)
	)
)

(instance bigJonScript of Script
	(properties)
	
	(method (doit)
		(cond 
			((Btst fDocBookingEvidence)
				0
			)
			((ego inRect: 165 105 200 112)
				(if (< state 2)
					(self changeState: 2)
				)
			)
			((> state 1)
				(self changeState: 0)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local6 0)
				(bigJon
					setLoop: -1
					setCel: -1
					setMotion: MoveTo 218 93 self
				)
			)
			(1
				(bigJon stopUpd:)
			)
			(2
				(= seconds (Random 1 4))
			)
			(3
				(bigJon
					setMotion: MoveTo 182 93 self
				)
			)
			(4
				(bigJon
					setLoop: 2
					setCel: 0
				)
				(RedrawCast)
				(cond 
					((cast contains: mrG)
						(LocPrint 2 31)
					)
					((or (<= gamePhase 1) local8)
						(switch (Random 0 3)
							(0
								(LocPrint 2 32 25 10)
							)
							(1
								(LocPrint 2 33 25 10)
							)
							(2
								(LocPrint 2 34 25 10)
							)
							(3
								(LocPrint 2 35 25 10)
							)
						)
						(= local6 1)
					)
					(else
						(= local8 1)
						(switch (Random 0 3)
							(0
								(LocPrint 2 36)
								(= local6 0)
							)
							(1
								(LocPrint 2 37)
								(= local6 5)
							)
							(2
								(LocPrint 2 38)
								(= local6 6)
							)
						)
					)
				)
			)
			(5
				(LocPrint 2 39 25 10)
				(= local6 2)
			)
			(6
				(LocPrint 2 40 25 10)
				(= local6 3)
			)
			(7
				(LocPrint 2 41 25 10)
				(= local6 3)
			)
			(8
				(LocPrint 2 42 25 10)
				(LocPrint 2 43 25 10)
				(= local6 0)
				(= seconds 2)
			)
			(9
				(= bookedEvidence 0)
				(ProcessEvidence 31 127 2 44)
				(ProcessEvidence 22 128 2 45)
				(ProcessEvidence 19 129 2 46)
				(ProcessEvidence 14 130 2 47)
				(ProcessEvidence 17 132 2 48)
				(ProcessEvidence 20 133 2 49)
				(ProcessEvidence 21 145 2 50)
				(ProcessEvidence 26 134 2 51)
				(ProcessEvidence 18 139 2 52)
				(ProcessEvidence 13 135 2 53)
				(ProcessEvidence 35 136 2 54)
				(ProcessEvidence 25 137 2 55)
				(ProcessEvidence 24 138 2 56)
				(GiveBlood)
				(if bookedEvidence
					(switch (Random 0 2)
						(0
							(LocPrint 2 57)
						)
						(1
							(LocPrint 2 58)
						)
						(2
							(LocPrint 2 59)
						)
					)
				else
					(LocPrint 2 60)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(event claimed?)
				(!= (event type?) saidEvent)
			)
				(return)
		)
		(cond 
			((Said 'gave,book,(turn<in),submit>')
				(= bookedEvidence 0)
				(cond 
					((cast contains: mrG)
						(event claimed: 1)
						(LocPrint 2 31)
					)
					((< state 4)
						(event claimed: 1)
						(Print 2 61)
					)
					((Said '/noword')
						(Print 2 62)
					)
					((Said '/clue')
						(LocPrint 2 63)
						(self changeState: 9)
					)
					(
						(and
							(ego has: 31)
							(Said '/9mm')
						)
							(ProcessEvidence 31 127 2 44)
					)
					(
						(and
							(ego has: 35)
							(Said '/card[<business]')
						)
							(ProcessEvidence 35 136 2 54)
					)
					((not (= temp0 (inventory saidMe: event)))
						(event claimed: 1)
						(DontHave)
					)
					((not (ego has: (inventory indexOf: temp0)))
						(DontHave)
					)
					(else
						(switch (inventory indexOf: temp0)
							(31
								(ProcessEvidence 31 127 2 44)
							)
							(22
								(ProcessEvidence 22 128 2 45)
							)
							(19
								(ProcessEvidence 19 129 2 46)
							)
							(14
								(ProcessEvidence 14 130 2 47)
							)
							(28
								(GiveBlood)
							)
							(17
								(ProcessEvidence 17 132 2 48)
							)
							(20
								(ProcessEvidence 20 133 2 49)
							)
							(21
								(ProcessEvidence 21 145 2 50)
							)
							(26
								(ProcessEvidence 26 134 2 51)
							)
							(18
								(ProcessEvidence 18 139 2 52)
							)
							(13
								(ProcessEvidence 13 135 2 53)
							)
							(35
								(ProcessEvidence 35 136 2 54)
							)
							(25
								(ProcessEvidence 25 137 2 55)
							)
							(24
								(ProcessEvidence 24 138 2 56)
							)
							(else
								(Print 2 64)
							)
						)
					)
				)
			)
			(
				(or
					(Said '/hello')
					(Said 'chat/john,dude,dude')
				)
				(cond 
					((cast contains: mrG)
						(LocPrint 2 31)
					)
					((not (ego inRect: 165 105 200 112))
						(Print 2 65))
					((<= (bigJonScript state?) 1)
						(Print 2 12)
					)
					((<= (bigJonScript state?) 3)
						(Print 2 66)
					)
					((< (bigJonScript state?) 8)
						(self cue:)
					)
					(else
						(self changeState: 5)
					)
				)
			)
			(
				(or
					(Said 'ask/briefcase,bin,locker')
					(Said 'ask/john/briefcase,bin')
				)
				(cond 
					((not (ego inRect: 165 105 200 112))
						(Print 2 65)
					)
					((<= (bigJonScript state?) 1)
						(Print 2 12)
					)
					((== (bigJonScript state?) 3)
						(Print 2 66)
					)
					(else
						(LocPrint 2 67)
					)
				)
			)
			(
				(or
					(Said 'ask/clue,finding')
					(Said 'ask/john/clue,finding')
					(Said 'get/finding')
				)
				(cond 
					((cast contains: mrG)
						(LocPrint 2 31)
					)
					((not (ego inRect: 165 105 200 112))
						(Print 2 65)
					)
					((<= (bigJonScript state?) 1)
						(Print 2 12)
					)
					((== (bigJonScript state?) 3)
						(Print 2 66)
					)
					(else
						(LocPrint 2 68)
					)
				)
			)
			((Said 'affirmative')
				(switch local6
					(1
						(LocPrint 2 69)
						(= local6 2)
					)
					(2
						(LocPrint 2 70)
					)
					(3
						(LocPrint 2 70)
					)
					(5
						(LocPrint 2 71)
					)
					(6
						(LocPrint 2 72)
					)
					(else
						(Print 2 73)
					)
				)
				(= local6 0)
			)
			((Said 'no')
				(switch local6
					(1
						(LocPrint 2 74)
					)
					(2
						(LocPrint 2 75)
					)
					(3
						(LocPrint 2 74)
					)
					(5
						(LocPrint 2 76)
					)
					(6
						(LocPrint 2 77)
					)
					(else
						(Print 2 73)
					)
				)
				(= local6 0)
			)
			((Said '/none')
				(switch local6
					(2
						(LocPrint 2 78)
					)
					(else
						(Print 2 73)
					)
				)
				(= local6 0)
			)
		)
	)
)

(instance mrGScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mrG stopUpd:)
			)
			(1
				(Print 2 79 #at -1 118)
				(switch (Random 0 2)
					(0
						(Print 2 80 #at -1 118)
					)
					(1
						(Print 2 81 #at -1 118)
					)
					(2
						(Print 2 82 #at -1 118)
					)
				)
			)
			(2
				(Print 2 79)
				(switch (Random 0 2)
					(0
						(Print 2 83 #at -1 118)
					)
					(1
						(Print 2 84 #at -1 118)
					)
					(2
						(Print 2 85 #at -1 118)
					)
				)
			)
			(3
				(Print 2 86 #at -1 118)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if
					(or
						(Said 'chat/gelepsi,dude,dude')
						(Said '/hello')
					)
					(switch (mrGScript state?)
						(0
							(self cue:)
						)
						(1
							(if (== (Random 0 3) 1)
								(self cue:)
							else
								(self changeState: 1)
							)
						)
						(2
							(if (== (Random 0 2) 1)
								(self cue:)
							else
								(self changeState: 2)
							)
						)
						(3 (self changeState: 3))
					)
				)
			)
		)
	)
)
