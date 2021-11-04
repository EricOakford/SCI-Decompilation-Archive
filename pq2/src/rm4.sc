;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
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
	rm4 0
)
(synonyms
	(cop detective)
	(basket box)
	(hall captain)
)

(local
	marieLetter
	wallet
	whereIsKeith
	egoSitting
	local4 ;which captain/keith comment to print based on gamePhaze?
	onPhone
	closedDrawer
	captainCanTalk
)
(procedure (LocPrint)
	(Print &rest #at -1 124)
)

(instance aTimer of Timer
	(properties)
)

(instance phone of Prop
	(properties)
)

(instance captain of Prop
	(properties)
)

(instance blab of Prop
	(properties)
)

(instance smoke of Prop
	(properties)
)

(instance rambo of View
	(properties)
)

(instance carKey of View
	(properties)
)

(instance bainsTheme of Sound
	(properties
		number 33
	)
)

(instance rm4 of Room
	(properties
		picture 4
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(HandsOn)
		(Load VIEW 1)
		(Load VIEW 0)
		(Load VIEW 65)
		(Load VIEW 62)
		(Load VIEW 3)
		(= gunFireState gunPROHIBITED)
		(NormalEgo)
		(if
			(and
				(< gamePhase 1)
				(or
					(== captainWarningTimer 1)
					(and
						gunSightsAligned
						(not (InRoom 0 5))
						(not (InRoom 10 2))
					)
					(and (== gamePhase 0) (== prevRoomNum 300))
				)
			)
			(= global162 0)
			(= talkedToCaptain 0)
			(Bset 32)
		)
		(if (and (< 5 gamePhase) (< gamePhase 8))
			(= marieWantsCall 1)
		)
		(= local4
			(cond 
				((Btst fVisitedHomicideOffice)
					(Bclr fVisitedHomicideOffice)
					2
				)
				(
					(and
						global127
						(not (Btst fReportedMarieMissingToCaptain)) ;fFlag31
					)
					8
				)
				((== gamePhase 8)
					4
				)
				(global162
					9
				)
				((== gamePhase 0) ;keith and captain mention bains exscape
					1
				)
				((== prevRoomNum 7)
					9
				)
				((== prevRoomNum 12)
					9
				)
				((== isOnDuty 1)
					2
				)
				((== isOnDuty 2)
					2
				)
				((== gamePhase 12)
					6
				)
				(marieWantsCall
					3
				)
				(else
					0
				)
			)
		)
		(= gunFireState 3)
		(self setScript: rm4Script)
		(self setLocales: 156)
		(ego view: (if (not gunDrawn) 1 else 7))
		(switch prevRoomNum
			(2
				(ego posn: 120 162 init: setMotion: MoveTo 120 10)
				(User prevDir: 1)
			)
			(7
				(ego posn: 238 148 loop: 3 init:)
			)
			(300
				(ego posn: 111 144 loop: 1 init:)
			)
			(12
				(HandsOff)
				(ego
					ignoreActors:
					illegalBits: 0
					view: 3
					loop: 3
					cel: 255
					posn: 182 130
					init:
					setCycle: BegLoop
				)
				(User canInput: 1)
				(phone
					cel: 255
					loop: 1
					posn: 172 116
					setCycle: BegLoop
					init:
				)
				(= egoSitting 1)
				(if
					(and
						(!= isOnDuty 1)
						(== gamePhase 7)
						(not (Btst 45)) ;fFlag45
					)
					(Bset 45)
					(captainScript changeState: 14)
				)
			)
			(else 
				(ego
					posn: 198 146
					init:
					setMotion: MoveTo 198 946
				)
			)
		)
	)
	
	(method (dispose)
		(drawerScript dispose:)
		(captainScript dispose:)
		(keithScript dispose:)
		(aTimer dispose: delete:)
		(super dispose:)
	)
)

(instance rm4Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(egoSitting 0)
			((<= (ego y?) 126)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(cond 
			((>= (ego y?) 165)
				(if
					(and
						(ego has: 3)
						(< 5 gamePhase)
						(< gamePhase 8)
					)
					(LocPrint 4 0)
					(LocPrint 4 1)
					(PutInRoom iUnmarkedCarKeys)
					(= workCarLocked 1)
				)
				(= global162 ;player waiting for reply maybe?
					(if
						(and
							talkedToCaptain
							(== isOnDuty 0)
						)
						(cond 
							((== local4 2)) ;returns 2?
							((== local4 1))
							((== local4 6))
							((== local4 8))
							(else (== local4 9))
						)
					else
						0
					)
				)
				(if (!= isOnDuty 1)
					(if (== gamePhase 6)
						(LocPrint 4 2)
						(= isOnDuty 1)
					)
				)
				(curRoom newRoom: 2)
			)
			(
				(and
					(== local4 1)
					(not talkedToKeith)
					(< (ego distanceTo: keith) 27)
				)
				(keithScript changeState: 0)
			)
			(
				(and
					(== local4 1)
					talkedToKeith
					(not talkedToCaptain)
					(< (ego distanceTo: captain) 64)
				)
				(captainScript changeState: 0)
			)
			(
				(and
					(== local4 8)
					(not (Btst fReportedMarieMissingToCaptain))
				)
					(captainScript changeState: 25)
				)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						closedDrawer
						(> local4 3)
					)
						(= local4 9)
						(= closedDrawer 0)
				)
				(switch local4
					(1
						(= whereIsKeith 2)
					)
					(2
						(= whereIsKeith 3)
					)
					(4
						(= whereIsKeith 2)
					)
					(6
						(= whereIsKeith 5)
					)
					(7
						(= whereIsKeith 3)
					)
					(8
						(= whereIsKeith 1)
					)
					(else 
						(= whereIsKeith (Random 1 6))
					)
				)
				(phone
					view: 3
					setLoop: 1
					setCel: 0
					posn: 172 116
					setPri: 9
					init:
					stopUpd:
				)
				(captain
					view: 65
					posn: 53 134
					cel: 0
					setPri: 9
					init:
				)
				(if (<= whereIsKeith 3)
					(captain
						loop: 3
						stopUpd:
					)
					(blab
						view: 65
						loop: 1
						posn: 48 112
						setPri: 12
						setCycle: Forward
						cycleSpeed: 2
						init:
					)
				else
					(captain
						loop: 4
						setCycle: Forward
						cycleSpeed: 7
					)
				)
				((= keith (View new:))
					view: 65
					init:
				)
				(switch whereIsKeith
					(1
						(keith
							view: 62
							posn: 213 134
							loop: 0
							cel: 0
						)
					)
					(2
						(keith
							posn: 210 136
							loop: 6
							cel: 0
						)
					)
					(3
						(keith
							posn: 133 130
							loop: 6
							cel: 0
						)
					)
					(4
						(keith
							posn: 163 148
							loop: 6
							cel: 0
						)
					)
					(5
						(keith
							posn: 120 140
							loop: 7
							cel: 0
						)
					)
					(6
						(keith
							posn: 214 148
							loop: 7
							cel: 0
						)
					)
				)
				(keith stopUpd:)
				(if (!= whereIsKeith 1)
					(smoke
						view: 65
						loop: 2
						posn:
							(if (> whereIsKeith 4)
								(+ (keith x?) 5)
							else
								(- (keith x?) 5)
							)
							(- (keith y?) 31)
						setPri: 9
						cycleSpeed: 3
						setCycle: Forward
						init:
					)
				)
				(rambo
					view: 65
					posn: 111 110
					loop: 5
					cel: (if (== (mod whereIsKeith 2) 0) 0 else 1)
					setPri: 7
					init:
					addToPic:
				)
				(carKey
					view: 65
					posn: (if (InRoom 3) 141 else 0) (if (InRoom 3) 110 else 0)
					loop: 5
					cel: 2
					init:
					stopUpd:
				)
				((View new:)
					view: 65
					posn: 183 135
					loop: 6
					cel: 1
					init:
					ignoreActors:
					addToPic:
				)
				(switch local4
					(2
						(cond 
							((== isOnDuty 1)
								(captainScript changeState: 14)
							)
							((== isOnDuty 2)
								(captainScript changeState: 12)
							)
							(else
								(captainScript changeState: 5)
							)
						)
					)
					(4
						(captainScript changeState: 16)
					)
					(6
						(captainScript changeState: 19)
					)
				)
			)
			(1
				(ego
					loop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(rm4 setScript: drawerScript)
			)
			(3
				(HandsOff)
				(ego
					posn: 182 130
					view: 3
					loop: 2
					cel: 3
					setCycle: BegLoop self
					setMotion: 0
				)
				(curRoom drawPic: 4)
			)
			(4
				(ego loop: 0)
				(User canInput: 1)
			)
			(5
				(ego
					loop: 3
					cel: 0
					setCycle: EndLoop self
				)
				(phone
					setCel: -1
					setCycle: EndLoop
					startUpd:
				)
			)
			(6
				(curRoom newRoom: 12)
			)
			(7
				(ego setCycle: BegLoop self)
				(phone setCycle: BegLoop)
			)
			(8 (ego
					loop: 0
					cel: 0
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said 'display,gave,tell/clue,list,note,threat,card,clue')
							(Said
								'display,gave,tell/hall/clue,list,note,threat,card,clue'
							)
						)
						(cond 
							((not (ego inRect: 70 131 122 156))
								(NotClose)
							)
							((not captainCanTalk)
								(Print 4 3)
							)
							(
								(or
									(ego has: iHitList)
									(ego has: iColbyCard)
									(Btst fBookedColbyCard)
								)
									(captainScript changeState: 28)
							)
							(else
								(Print 4 4)
							)
						)
					)
					((Said 'look,read/file')
						(if (ego inRect: 71 130 124 157)
							(Print 4 5)
						else
							(Print 4 6)
						)
					)
					((Said 'look,read/newspaper,flyer')
						(cond 
							((ego inRect: 122 117 168 124)
								(Print 4 7)
								(Print 4 8)
								(SolvePuzzle 1 123)
							)
							(
								(and
									(ego inRect: 70 128 99 140)
									(== (ego loop?) 1)
								)
									(Print 4 9)
							)
							((ego inRect: 71 130 124 157)
								(Print 4 10)
								(Print 4 11)
								(SolvePuzzle 1 124)
							)
							(else
								(event claimed: 0)
							)
						)
					)
					(
						(or
							(Said 'look,read/anyword<ya<thank')
							(Said 'look,read/letter')
						)
						(if
							(and
								(ego has: iThankYouLetter)
								(!= local4 3)
							)
								((inventory at: iThankYouLetter) showSelf:)
						else
							(event claimed: 0)
						)
					)
					((Said 'chat[/dude,cop,person]')
						(cond 
							((ego inRect: 70 131 122 156)
								(Print 4 12)
							)
							((ego inRect: 90 117 137 132)
								(Print 4 13)
							)
							((< (ego distanceTo: keith) 46)
								(Print 4 14)
							)
							(else
								(Print 4 15)
							)
						)
					)
					(
						(or
							(Said 'ask,get//order')
							(Said 'ask,get/order')
						)
							(Print 4 16)
					)
					((Said 'chat,ask/hall')
						(if (!= isOnDuty 1)
							(if (== (captain loop?) 3)
								(Print 4 17)
							else
								(Print 4 18)
							)
						else
							(captainScript changeState: 14)
						)
					)
					((Said 'chat,ask/james,pierson')
						(if (ego inRect: 90 117 137 132)
							(Print 4 19)
						else
							(NotClose)
						)
					)
					(
						(or
							(Said 'chat,get,ask/friend,friend')
							(Said 'let/go')
						)
						(if (< (ego distanceTo: keith) 40)
							(switch isOnDuty
								(2
									(Print 4 20)
								)
								(1
									(Print 4 21)
								)
								(else
									(Print 4 22)
								)
							)
						else
							(Print 4 23)
						)
					)
					((Said 'press,move/friend')
						(Print 4 24)
					)
					((Said 'get,remove,pick/')
						(if (ego inRect: 70 131 122 153)
							(Print 4 25)
						else
							(event claimed: 0)
						)
					)
					((Said '/briefcase')
						(Print 4 26)
					)
					(
						(or
							(Said '/shot<mug')
							(Said '/mugshot')
						)
							(Print 4 27))
					((Said 'turn<on/computer')
						(Print 4 28)
					)
					((Said 'look,use/computer')
						(if
							(or
								(== (ego onControl:) cYELLOW) ;16384
								(== (ego onControl:) -16384) ;not needed?
							)
								(curRoom newRoom: 8)
						else
							(NotClose)
						)
					)
					((Said 'look,read,frisk>')
						(cond 
							((Said '/light,lamp')
								(Print 4 29)
							)
							((Said '<in/drawer')
								(Print 4 30)
							)
							((Said '/drawer')
								(Print 4 31)
							)
							((Said '/cone')
								(if (== (captain loop?) 3)
									(Print 4 32)
								else
									(Print 4 33)
								)
							)
							((Said '/locker')
								(Print 4 34)
							)
							((Said '/key,coatrack')
								(Print 4 35)
							)
							((Said '/wall')
								(Print 4 36)
							)
							((Said '/file,cabinet<in,in')
								(Print 4 37)
							)
							((Said '/board[<bulletin]')
								(cond 
									((ego inRect: 122 117 168 124)
										(Print 4 7)
										(Print 4 8)
										(SolvePuzzle 1 123)
									)
									(
										(and
											(ego inRect: 70 128 99 140)
											(== (ego loop?) 1)
										)
											(Print 4 38)
									)
									(else
										(Print 4 39)
									)
								)
							)
							((Said '/score,schedule')
								(SolvePuzzle 1 123)
								(Print 4 8)
							)
							((Said '[<at,around][/(!*,chamber,office,homicide)]')
								(Print 4 40)
								(Print 4 41)
							)
						)
					)
				)
				(cond 
					((Said 'look,get,read,check/basket,subpoena,note,envelope,finding,newspaper')
						(event claimed: 0)
						(if
							(or
								(ego inRect: 130 123 188 144)
								egoSitting
							)
							(cond 
								(
									(and
										(< gamePhase 6)
										(Said '/basket,subpoena,envelope,newspaper')
									)
										(SolvePuzzle 1 64)
										(LocPrint 4 42)
										(LocPrint 4 43)
										(LocPrint 4 44 70 240)
										(Print 4 45 #at -1 120)
								)
								(
									(and
										(>= gamePhase 8)
										(Said '/basket,envelope,finding,newspaper')
									)
									(if
										(or
											(Btst fBookedFingerprint) ;128
											(Btst fBookedCoveBlood) ;was 131, which never gets set. Confirm 146 is correct here.
											(Btst fBookedPlasterCast) ;132
											(Btst fBookedKnife) ;130
											(Btst fBookedJailClothes) ;134
											(Btst fBookedRevolver) ;127
											(Btst fBookedBullets) ;133
											(Btst fBppkedThumbprint) ;129
										)
											(SolvePuzzle 3 65)
											(LocPrint 4 46)
											(LocPrint 4 47)
											(Print 4 48 #at -1 40)
											(if (Btst fBookedFingerprint)
												(Print 4 49)
											)
											(if (Btst fBppkedThumbprint)
												(Print 4 50)
											)
											(cond 
												((Btst fBookedCoveBlood) ;was 131. See above comment
													(Print 4 51)
												)
												((Btst fBookedPlasterCast)
													(Print 4 52)
												)
												((Btst fBookedKnife)
													(Print 4 53)
												)
												((Btst fBookedJailClothes)
													(Print 4 54)
												)
												((Btst fBookedRevolver)
													(Print 4 55)
												)
												((Btst fBookedBullets)
													(Print 4 56)
												)
											)
											(Print 4 57)
									else
										(Print 4 58)
									)
								)
								(
									(and
										marieWantsCall
										(Said '/basket,note,letter,newspaper')
									)
									(LocPrint 4 59)
									(LocPrint 4 60)
									(LocPrint 4 61)
								)
								(else
									(Print 4 62)
									(event claimed: 1)
								)
							)
						else
							(Print 4 63)
							(event claimed: 1)
						)
					)
					((Said 'look/dude,person,cop')
						(cond 
							((ego inRect: 75 131 122 153)
								(Print 4 64)
							)
							((ego inRect: 90 117 142 132)
								(Print 4 65)
							)
							((< (ego distanceTo: keith) 30)
								(Print 4 66)
							)
							(else
								(Print 4 67)
							)
						)
					)
					((Said 'look/hall')
						(Print 4 64)
					)
					((Said 'look/james')
						(if (ego inRect: 90 117 142 132)
							(Print 4 65)
						)
					)
					((Said 'look/friend')
						(Print 4 66)
					)
					((Said 'look/desk')
						(cond 
							((ego inRect: 71 131 122 156)
								(Print 4 68)
							)
							((ego inRect: 90 117 140 132)
								(Print 4 69)
							)
							((ego inRect: 189 117 244 139)
								(Print 4 70)
							)
							((ego inRect: 154 140 235 200)
								(Print 4 71)
							)
							(else
								(Print 4 72)
							)
						)
					)
					((Said 'get,remove/subpoena')
						(if (< gamePhase 6)
							(Print 4 73)
						else
							(Print 4 74)
						)
					)
					((Said 'get,remove/note')
						(if (== gamePhase 6)
							(Print 4 75)
						else
							(Print 4 76)
						)
					)
					((Said 'get,remove/clue,finding,envelope')
						(if (>= gamePhase 8)
							(Print 4 73)
						else
							(Print 4 77)
						)
					)
					((Said 'read,look/schedule[<fire]')
						(if (ego inRect: 122 117 168 124)
							(Print 4 8)
						else
							(NotClose)
						)
					)
					((Said 'open,close/locker')
						(Print 4 78)
					)
					(
						(or
							(Said 'open/cabinet[<file]')
							(and
								(ego inRect: 227 138 260 152)
								(Said 'open/drawer<file')
							)
						)
							(if (ego inRect: 232 138 260 152)
								(cast eachElementDo: #startUpd) ;why??
								(curRoom newRoom: 7)
							else
								(NotClose)
							)
					)
					((Said 'close/(cabinet[<file]),file')
						(if (== prevRoomNum 7)
							(Print 4 79)
						else
							(Print 4 80)
						)
					)
					((Said 'get,remove/key')
						(if (ego inRect: 122 117 168 124)
							(if (ego has: iUnmarkedCarKeys)
								(Print 4 81)
							else
								(ego get: iUnmarkedCarKeys)
								(carKey posn: 0 0)
								(Print 4 82)
								(SolvePuzzle 1 104)
							)
						else
							(NotClose)
						)
					)
					((Said 'deposit,replace/key')
						(if (ego has: iUnmarkedCarKeys)
							(if (ego inRect: 122 117 168 124)
								(PutInRoom iUnmarkedCarKeys)
								(carKey posn: 141 110)
								(Print 4 83)
							else
								(NotClose)
							)
						else
							(DontHave)
						)
					)
					((Said 'sat')
						(cond 
							(egoSitting
								(Print 4 84)
							)
							((ego inRect: 170 122 196 141)
								(HandsOff)
								(User canInput: 1)
								(= egoSitting 1)
								(= gunDrawn 0)
								(ego
									view: 3
									loop: 0
									cel: 0
									setMotion: 0
									setCycle: 0
									ignoreActors:
									illegalBits: 0
									posn: 182 130
								)
							)
							(else
								(NotClose)
							)
						)
					)
					(
						(or
							(Said 'stand[<up]')
							(Said 'get<up')
						)
							(if egoSitting
								(HandsOn)
								(NormalEgo)
								(ego
									view: 1
									loop: 1
									cel: 9
									posn: 192 136
								)
								(= egoSitting 0)
							else
								(Print 4 85)
							)
					)
					((Said 'open/drawer,desk')
						(if egoSitting
							(if (not (Btst fEgoDeskLocked)) ;renamed from fEgoDeskUnlocked
								(self changeState: 1)
							else
								(Print 4 86)
							)
						else
							(Print 4 87)
						)
					)
					((Said 'unlock/drawer,desk')
						(if egoSitting
							(cond 
								((not (Btst fEgoDeskLocked))
									(Print 4 88)
								)
								((ego has: iKeyRing)
									(Print 4 89)
									(Bclr fEgoDeskLocked)
									(self changeState: 1))
								(else
									(Print 4 90)
								)
							)
						else
							(Print 4 91)
						)
					)
					((Said 'lock/drawer,desk')
						(if egoSitting
							(cond 
								((Btst fEgoDeskLocked)
									(Print 4 92)
								)
								((ego has: iKeyRing)
									(Print 4 83)
									(Bset fEgoDeskLocked)
								)
								(else
									(Print 4 90)
								)
							)
						else
							(Print 4 91)
						)
					)
					(
						(or
							(Said 'lock,close/drawer')
							(Said 'stand')
						)
						(if egoSitting
							(if (== (rm4 script?) drawerScript)
								(self changeState: 3)
							else
								(Print 4 93)
							)
						else
							(Print 4 94)
						)
					)
					((Said 'use,get,dial,(pick<up)/phone')
						(if egoSitting
							(self changeState: 5)
							(= onPhone 1)
						else
							(Print 4 95)
						)
					)
					(
						(or
							(Said 'deposit[<down]/phone')
							(Said 'replace/phone')
							(Said 'hang<up[/phone]')
						)
						(if (and onPhone egoSitting)
							(= onPhone 0)
							(self changeState: 7)
						else
							(Print 4 95)
						)
					)
					(
						(and
							(Said 'call,phone/')
							(not onPhone)
						)
							(Print 4 96)
					)
				)
			)
		)
	)
)

(instance drawerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #hide)
				(curRoom drawPic: 12)
				(if (InRoom iThankYouLetter 12)
					((= marieLetter (View new:))
						view: 59
						loop: 0
						cel: 2
						posn: 146 115
						init:
						ignoreActors:
						stopUpd:
					)
				)
				(if (InRoom iWallet 12)
					((= wallet (View new:))
						view: 59
						loop: 0
						cel: 0
						posn: 190 105
						init:
						ignoreActors:
						stopUpd:
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'get,remove/letter,card')
						(if (InRoom iThankYouLetter 12)
							(marieLetter dispose:)
							(Print 4 83 #draw)
							(ego get: iThankYouLetter)
							(SolvePuzzle 1 106)
						else
							(Print 4 97)
						)
					)
					((Said 'get,remove/note')
						(if (InRoom iThankYouLetter 12)
							(Print 4 98)
						else
							(Print 4 97)
						)
					)
					((Said 'get/billfold')
						(if (InRoom iWallet 12)
							(wallet dispose:)
							(Print 4 83 #draw)
							(ego get: iWallet)
							(SolvePuzzle 1 105)
						else
							(Print 4 97)
						)
					)
					((Said 'deposit,replace/letter,card')
						(if (ego has: iThankYouLetter)
							((= marieLetter (View new:))
								view: 59
								posn: 146 115
								loop: 0
								cel: 2
								init:
								ignoreActors:
								stopUpd:
							)
							(ego put: iThankYouLetter 12)
						else
							(Print 4 99)
						)
					)
					((Said 'deposit,replace/billfold')
						(if (ego has: iWallet)
							((= wallet (View new:))
								view: 59
								posn: 190 105
								loop: 0
								cel: 0
								init:
								stopUpd:
							)
							(ego put: 7 12)
						else
							(DontHave)
						)
					)
					((Said 'look,frisk[/drawer]')
						(inventory
							carrying: {Your desk drawer contains:}
							empty: {Your desk drawer is empty.}
							showSelf: 12
						)
					)
					(
						(or
							(Said 'look,read/*<ya<thank')
							(Said 'look,read/letter')
						)
						(if (InRoom iThankYouLetter 12)
							(Print 4 100)
						else
							(event claimed: 0)
						)
					)
					(
						(or
							(Said '/shot<mug')
							(Said '/mugshot')
						)
							(Print 4 27)
						)
					((Said 'close[/drawer]')
						(curRoom drawPic: (curRoom picture?))
						(cast eachElementDo: #dispose)
						(cast eachElementDo: #delete)
						(ego init:)
						(= closedDrawer 1)
						(rm4 setScript: rm4Script)
						(rm4Script changeState: 3)
					)
				)
			)
		)
	)
)

(instance captainScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(User canInput: 0)
				(ego stopUpd:)
				(= talkedToCaptain 1)
				(captain setCycle: EndLoop)
				(aTimer setCycle: self 18)
			)
			(1
				(LocPrint 4 101)
				(aTimer setCycle: self 18)
			)
			(2
				(LocPrint 4 102)
				(aTimer setCycle: self 18)
			)
			(3
				(LocPrint 4 103)
				(aTimer setCycle: self 10)
			)
			(4
				(LocPrint 4 104)
				(if egoSitting (User canInput: 1) else (HandsOn))
				(ego startUpd:)
				(captain setCycle: BegLoop)
			)
			(5 (aTimer setReal: self 3))
			(6
				(if (not egoSitting)
					(ego loop: 1)
				)
				(HandsOff)
				(User canInput: 0)
				(= gamePhase 1)
				(= captainWarningTimer 300)
				(= isOnDuty 2)
				(= talkedToCaptain 1)
				(captain setCycle: EndLoop)
				(aTimer setCycle: self 18)
			)
			(7
				(Print 4 105 #at -1 130 #draw)
				(aTimer setCycle: self 18)
				(bainsTheme play:)
			)
			(8
				(LocPrint 4 106)
				(aTimer setCycle: self 18)
			)
			(9
				(LocPrint 4 107)
				(aTimer setCycle: self 18)
			)
			(10
				(LocPrint 4 108)
				(aTimer setCycle: self 18)
			)
			(11
				(LocPrint 4 109)
				(if egoSitting
					(User canInput: 1)
				else
					(HandsOn)
				)
				(= isOnDuty 2)
				(captain setCycle: BegLoop)
			)
			(12
				(HandsOff)
				(User canInput: 0)
				(if (not egoSitting)
					(ego loop: 1)
				)
				(= talkedToCaptain 1)
				(captain setCycle: EndLoop)
				(aTimer setCycle: self 18)
			)
			(13
				(switch (Random 0 2)
					(0
						(LocPrint 4 110)
					)
					(1
						(LocPrint 4 111)
					)
					(2
						(LocPrint 4 112)
					)
				)
				(if egoSitting
					(User canInput: 1)
				else
					(HandsOn)
				)
				(ego startUpd:)
				(captain setCycle: BegLoop)
			)
			(14
				(HandsOff)
				(User canInput: 0)
				(if (not egoSitting)
					(ego loop: 1)
				)
				(= talkedToCaptain 1)
				(= isOnDuty 1)
				(captain setCycle: EndLoop)
				(aTimer setCycle: self 18)
			)
			(15
				(switch (Random 0 1)
					(0
						(LocPrint 4 113)
					)
					(1
						(LocPrint 4 114)
					)
				)
				(if egoSitting
					(User canInput: 1)
				else
					(HandsOn)
				)
				(captain setCycle: BegLoop)
			)
			(16
				(HandsOff)
				(User canInput: 0)
				(ego setMotion: MoveTo 60 134)
				(= talkedToCaptain 1)
				(= isOnDuty 2)
				(= gamePhase 10)
				(= captainWarningTimer 600)
				(captain setCycle: EndLoop)
				(aTimer setCycle: self 18)
			)
			(17
				(ego loop: 1)
				(Print 4 115 #at -1 130 #draw)
				(LocPrint 4 116)
				(ego startUpd:)
				(aTimer setCycle: self 18)
			)
			(18
				(LocPrint 4 117)
				(captain setCycle: BegLoop)
				(if egoSitting
					(User canInput: 1)
				else
					(HandsOn)
				)
				(ego setMotion: 0)
			)
			(19
				(HandsOff)
				(= talkedToCaptain 1)
				(captain setCycle: BegLoop)
				(blab
					view: 65
					loop: 1
					posn: 48 112
					setPri: 12
					setCycle: Forward
					cycleSpeed: 2
					init:
				)
				(aTimer setCycle: self 18)
			)
			(20
				(ego loop: 1)
				(Print 4 118 #at -1 130 #draw)
				(LocPrint 4 119)
				(aTimer setCycle: self 18)
			)
			(21
				(LocPrint 4 120)
				(LocPrint 4 121)
				(aTimer setCycle: self 18)
			)
			(22
				(LocPrint 4 122)
				(aTimer setCycle: self 18)
			)
			(23
				(LocPrint 4 123)
				(aTimer setCycle: self 18)
			)
			(24
				(LocPrint 4 124)
				(blab dispose:)
				(captain setCycle: Forward)
				(HandsOn)
			)
			(25
				(Bset fReportedMarieMissingToCaptain)
				(bainsTheme play:)
				(HandsOff)
				(ego loop: 1)
				(if (not (Btst fKidnappingReported))
					(Print 4 125 #at -1 130 #draw)
				)
				(captain setCycle: EndLoop)
				(aTimer setCycle: self 18)
			)
			(26
				(ego loop: 1)
				(Print 4 126 #at -1 130 #draw)
				(aTimer setCycle: self 8)
			)
			(27
				(LocPrint 4 127)
				(blab hide:)
				(HandsOn)
				(= captainCanTalk 1)
			)
			(28
				(blab show: setCycle: Forward)
				(aTimer setCycle: self 9)
			)
			(29
				(if (ego has: iHitList)
					(LocPrint 4 128)
				else
					(LocPrint 4 129)
					(LocPrint 4 130)
				)
				(aTimer setCycle: self 12)
			)
			(30
				(Print 4 131 #at -1 120)
				(LocPrint 4 132)
				(aTimer setCycle: self 18)
			)
			(31
				(cond 
					((ego has: iColbyCard)
						(LocPrint 4 133)
						(LocPrint 4 134)
						(LocPrint 4 135)
					)
					((Btst fBookedColbyCard)
						(LocPrint 4 136)
						(LocPrint 4 134)
						(LocPrint 4 135)
					)
					(else (LocPrint 4 137))
				)
				(aTimer setCycle: self 18)
			)
			(32
				(if (or (ego has: iColbyCard) (Btst fBookedColbyCard))
					(= gamePhase 13)
				)
				(if (or (ego has: iColbyCard) (ego has: iHitList))
					(if (ego has: iColbyCard)
						(PutInRoom iColbyCard 2)
					)
					(if (ego has: iHitList)
						(PutInRoom iHitList 2)
					)
					(LocPrint 4 138)
				)
				(captain setCycle: BegLoop)
			)
		)
	)
)

(instance keithScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: 0 canControl: 0)
				(= talkedToKeith 1)
				(Print 4 139 #at 110 40 #draw)
				(Print 4 140 #at 110 40)
				(Print 4 141 #at 110 40)
				(Print 4 142 #at 110 40)
				(User canInput: 1 canControl: 1)
			)
		)
	)
)
