;;; Sierra Script 1.0 - (do not remove this comment)
(script# 49)
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
	Room49 0
)

(local
	aOgre
	local1
	ogress
	hen
	dog
	closetDoor
	frontDoor
	bone
	burp
	local9
	local10
	wokeUpOgre
)
(instance theMusic of Sound)

(instance throwSound of Sound)

;EO: This room uses Avoider, but there is not enough heap space to use it.
; Hopefully, it's not needed.

(instance Room49 of Room
	(properties
		picture 49
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 541)
		(= currentStatus egoNormal)
		(if (not ((Inventory at: iBone) ownedBy: 49))
			(Load VIEW 351)
			(Load VIEW 352)
			(Load VIEW 87)
			(Load VIEW 15)
		)
		(self setRegions: OGRE_HOUSE)
		(super init:)
		(if (and (> ogreState 0) (!= ogreState 5) (not enteredOgreKitchen))
			(= local1 1)
		)
		(= isIndoors TRUE)
		(= noWearCrown TRUE)
		(if isNightTime
			((View new:)
				view: 616
				loop: 1
				posn: 50 128
				ignoreActors: TRUE
				addToPic:
				stopUpd:
			)
		)
		((Prop new:)
			view: 541
			loop: (if isNightTime 1 else 2)
			cel: 0
			posn: 86 136
			ignoreActors: 1
			setPri: 12
			setCycle: Forward
			init:
			addToPic:
		)
		((= dog (Actor new:))
			view: 351
			illegalBits: 0
			loop: 1
			setStep: 5 2
			posn: (if (== ((inventory at: iBone) owner?) 49) 155 else 160) 126
			init:
		)
		(if (== ogreState 5)
			((inventory at: iMagicHen) owner: 48)
		)
		(if ((inventory at: iMagicHen) ownedBy: 49)
			((= hen (Actor new:))
				view: 360
				posn: 230 129
				init:
				hide:
			)
			(hen setScript: henPecked)
		)
		((= closetDoor (Prop new:))
			view: 615
			loop: 0
			cel: 0
			posn: 209 123
			init:
			stopUpd:
		)
		((= frontDoor (Prop new:))
			view: 616
			ignoreActors: TRUE
			loop: 0
			posn: 59 129
			init:
		)
		(if (or (== prevRoomNum 4) (== prevRoomNum 0))
			(frontDoor setPri: 0 ignoreActors: TRUE)
			(= ogreFrontDoorOpen TRUE)
			(ego
				posn: 53 132
				view: 2
				loop: 0
				setStep: 3 2
				illegalBits: cWHITE
				init:
			)
			(if (or (> gamePhase getTheHen) (ego has: iMagicHen))
				(Load VIEW 245)
				(Load VIEW 48)
				((= ogress (Actor new:))
					view: 245
					posn: 65 131
					setStep: 4 2
					init:
					setScript: ogressChase
				)
			)
		)
		(if (== prevRoomNum 48)
			(ego
				posn: 181 69
				view: 2
				loop: 1
				setStep: 3 2
				illegalBits: cWHITE
				init:
			)
		)
		(if (== prevRoomNum 50)
			(ego
				posn: 267 131
				view: 2
				setStep: 3 2
				illegalBits: cWHITE
				init:
			)
			(if enteredOgreKitchen
				(Load VIEW 245)
				(Load VIEW 48)
				((= ogress (Actor new:))
					view: 245
					illegalBits: cWHITE
					posn: 301 131
					setStep: 4 2
					init:
					setScript: ogressChase
				)
			)
			(if (or (== ogreState 1) (== ogreState 90))
				(self setScript: ogreStuff)
			)
		)
		(if (== prevRoomNum 51)
			(ego
				view: 2
				setStep: 3 2
				loop: 2
				illegalBits: cWHITE
				posn: 225 128
				init:
				setCycle: Walk
				setMotion: 0
			)
			(if (== ogreState 5)
				(Load VIEW 245)
				(Load VIEW 48)
				((= ogress (Actor new:))
					view: 245
					illegalBits: cWHITE
					posn: 301 131 4 2
					init:
					setScript: ogressChase
				)
			)
			(if (== ogreState 4)
				(Load VIEW 250)
				(Load VIEW 78)
				(Load VIEW 79)
				((= aOgre (Actor new:))
					view: 250
					posn: 235 132
					setStep: 6 2
					init:
					setCycle: Walk
				)
				(= ogreFrontDoorOpen 0)
			)
		)
		(if (== ogreState 2)
			(= ogreFrontDoorOpen 0)
			(frontDoor setPri: -1 cel: 0)
			(Load VIEW 250)
			(Load VIEW 78)
			(Load VIEW 79)
			(Load VIEW 256)
			((= aOgre (Actor new:))
				view: 256
				posn: 201 158
				setStep: 6 2
				init:
				cycleSpeed: 2
				setCycle: Forward
			)
			(aOgre setScript: ogreAwake)
		)
		(if (== ogreState 3)
			(= ogreFrontDoorOpen FALSE)
			(frontDoor setPri: -1 cel: 0)
			(Load VIEW 250)
			(Load VIEW 78)
			(Load VIEW 79)
			((= aOgre (Actor new:))
				illegalBits: 0
				view: 256
				posn: 201 158
				setStep: 6 2
				init:
				cycleSpeed: 2
				setCycle: Forward
			)
			(aOgre setScript: ogreAwake)
		)
		(frontDoor
			cel: (if ogreFrontDoorOpen (frontDoor lastCel:) else 0)
			init:
			stopUpd:
		)
		(if (not (frontDoor cel?))
			(ego observeControl: cYELLOW)
		else
			(ego ignoreControl: cYELLOW)
		)
		(if (== ((inventory at: iBone) owner?) 49)
			(dog setScript: chewBone)
		else
			(dog setScript: chaseEgo)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cGREEN)
			(ego setPri: 4)
		else
			(ego setPri: -1)
		)
		(if
			(and
				ogreState
				(> (ego x?) 111)
				(> (ego y?) 123)
				(!= ogreState 5)
				(!= (self script?) ogreStuff)
				(!= (self script?) ogreAwake)
			)
			(if
				(not
					(if (== prevRoomNum 50)
						(if (== ogreState 4) else (== ogreState 1))
					)
				)
				(self setScript: ogreStuff)
			)
		)
		(if (& (ego onControl: 0) cBROWN)
			(HandsOn)
			(curRoom newRoom: 4)
		)
		(if (and (& (ego onControl: 0) cRED) (not local9))
			(HandsOn)
			(curRoom newRoom: 51)
		)
		(if (& (ego onControl: 0) cCYAN)
			(HandsOn)
			(curRoom newRoom: 48)
		)
		(if (and (& (ego onControl: 0) cMAGENTA) (not local9))
			(HandsOn)
			(curRoom newRoom: 50)
		)
		(if
			(and
				(& (ego onControl: 0) cLGREEN)
				(!= currentStatus egoOnStairs)
				(not local9)
			)
			(= currentStatus egoOnStairs)
			(HandsOff)
			(moveOnTheStairs changeState: 1)
		)
	)
	
	(method (dispose)
		(HandsOn)
		(= noWearCrown FALSE)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp index)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'look>')
							(cond 
								((Said '<under/table')
									(Print 49 0)
								)
								((Said '/table')
									(if aOgre
										(Print 49 1)
									else
										(Print 49 2)
									)
								)
								((Said '/chair')
									(Print 49 3)
								)
								((Said '/carpet,carpet')
									(Print 49 4)
								)
								((Said '/stair')
									(Print 49 5)
								)
								((Said '/window')
									(Print 49 6)
								)
								((Said '/door')
									(Print 49 7)
								)
								((Said '/wall')
									(Print 49 8)
								)
								((or (Said '/dirt') (Said '<down'))
									(Print 49 9)
								)
								((Said '/giant')
									(cond 
										((== ogreState ogreSLEEPING)
											(Print 49 1)
										)
										(ogreState
											(Print 49 10)
										)
										(else
											(Print 49 11)
										)
									)
								)
								((Said '/bulldog')
									(if (== (dog script?) chewBone)
										(Print 49 12)
									else
										(Print 49 13)
									)
								)
								((Said '[<around][/room,cottage]')
									(Print 49 14)
								)
							)
						)
						((Said 'blow/whistle')
							(if (and (ego has: iWhistle) (cast contains: aOgre))
								(if (== ogreState ogreSLEEPING)
									(Print 49 15)
								else
									(Print 49 16)
								)
							else
								(event claimed: FALSE)
							)
						)
						((Said 'converse/bulldog')
							(if (== (dog script?) chewBone)
								(Print 49 17)
							else
								(Print 49 18)
							)
						)
						((Said 'kill/bulldog')
							(Print 49 19)
						)
						((Said 'pat/bulldog')
							(Print 49 20)
						)
						((Said 'calm/bulldog')
							(Print 49 21)
						)
						((Said 'get,capture/bulldog')
							(Print 49 22)
						)
						((Said 'sit>')
							(Print 49 23)
							(event claimed: TRUE)
						)
						;NOTE: this case already exists, and will probably never be executed
;;;						((Said 'converse/bulldog')
;;;							(if (== (dog script?) chewBone)
;;;								(Print 49 17)
;;;							else
;;;								(Print 49 18)
;;;							)
;;;						)
						((Said 'converse,awaken[/giant]')
							(if (cast contains: aOgre)
								(if (== ogreState ogreSLEEPING)
									(if
										(and
											(< (ego distanceTo: aOgre) 50)
											(not (ogreAwake state?))
										)
										(Print 49 24)
										(= wokeUpOgre TRUE)
										(ogreAwake cue:)
									else
										(Print 49 25)
									)
								else
									(Print 49 26)
								)
							else
								(Print 49 27)
							)
						)
						((or (Said 'open/closet') (Said 'open/door<closet'))
							(cond 
								((not (ego inRect: 219 123 245 130))
									(NotClose)
								)
								((closetDoor cel?)
									(Print 49 28)
								)
								((or (cast contains: ogress) (cast contains: aOgre))
									(Print 49 29)
								)
								((ego has: iMagicHen)
									(Print 49 10)
								)
								(else
									(closetDoor setScript: doorOpen)
								)
							)
						)
						((Said 'open/door')
							(cond 
								((ego inRect: 219 123 245 130)
									(cond 
										((closetDoor cel?)
											(Print 49 28)
										)
										((or (cast contains: ogress) (cast contains: aOgre))
											(Print 49 29)
										)
										((ego has: iMagicHen)
											(Print 49 10)
										)
										(else
											(closetDoor setScript: doorOpen)
										)
									)
								)
								((ego inRect: 38 129 73 141)
									(if (not (frontDoor cel?))
										(cond 
											((ego has: iMagicHen)
												(frontDoor ignoreActors: TRUE setCycle: EndLoop setPri: 0)
												(= ogreFrontDoorOpen TRUE)
												(ego ignoreControl: cYELLOW)
												(if (and (ego has: iMagicHen) (== (ogreAwake state?) 0))
													(ogreAwake changeState: 2)
												)
											)
											((cast contains: aOgre)
												(if (and (== ogreState ogreSLEEPING) (not (ogreAwake state?)))
													(ogreAwake changeState: 2)
												)
											)
											(else
												(frontDoor ignoreActors: TRUE setCycle: EndLoop setPri: 0)
												(= ogreFrontDoorOpen TRUE)
												(ego ignoreControl: cYELLOW)
											)
										)
									else
										(Print 49 30)
									)
								)
								(else
									(Print 49 31)
								)
							)
						)
						((Said 'close/door')
							(cond 
								((ego inRect: 219 123 245 130)
									(if (closetDoor cel?)
										(closetDoor setCycle: BegLoop)
									else
										(Print 49 32)
									)
								)
								((ego inRect: 38 129 73 141)
									(if (frontDoor cel?)
										(if (ego has: iMagicHen)
											(Print 49 33)
										else
											(= ogreFrontDoorOpen FALSE)
											(ego observeControl: cYELLOW)
											(frontDoor setCycle: BegLoop setPri: -1)
										)
									else
										(Print 49 32)
									)
								)
								(else
									(Print 49 31)
								)
							)
						)
						((Said 'get/egg[<gold]')
							(if aOgre
								(if (< (ego distanceTo: aOgre) 20)
									(ego put: iMagicHen 49)
									(ogreAwake changeState: 1)
								else
									(NotClose)
								)
							else
								(Print 49 34)
							)
						)
						((Said 'get,rob/chicken')
							(cond 
								((not hen)
									(Print 49 35)
								)
								((ego has: iMagicHen)
									(AlreadyTook)
								)
								(
									(and
										(ego inRect: 190 148 264 156)
										(> (ego x?) (- (hen x?) 11))
										(< (ego x?) (+ (hen x?) 11))
									)
									(= gotItem TRUE)
									(ego get: iMagicHen)
									(theGame changeScore: 4)
									(hen dispose:)
								)
								(else
									(NotClose)
								)
							)
						)
						((Said 'kill/giant')
							(Print 49 36)
						)
						(
							(or
								(Said 'kiss/giant')
								(Said 'kiss/bulldog')
								(Said 'kiss[/noword]')
							)
							(Print 49 37)
						)
						((Said 'throw,deliver,feed/bone')
							(if (ego has: iBone)
								(if (> (ego distanceTo: dog) 40)
									(HandsOff)
									(dog setMotion: 0 setCycle: 0 setScript: 0)
									((inventory at: iBone) moveTo: 49)
									(theGame changeScore: 4)
									(dog setScript: catchBone)
								else
									(Print 49 38)
								)
							else
								(Print 49 39)
							)
						)
						((Said 'get,rob/bone')	;EO: fixed said spec
							(if (== ((inventory at: iBone) owner?) 49)
								(Print 49 40)
							else
								(Print 49 41)
							)
						)
						((Said 'get,capture/giant')
							(Print 49 42)
						)
						(
							(and
								(Said 'deliver>')
								(= index (inventory saidMe:))
							)
							(if
								(and
									index
									(ego has: (inventory indexOf: index))
								)
								(if (cast contains: aOgre)
									(Print 49 43)
								else
									(Print 49 44)
								)
								(event claimed: TRUE)
							else
								(Print 49 45)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (== n 4)
			((ScriptID OGRE_HOUSE) keep: FALSE)
			(= noWearCrown FALSE)
		)
		(super newRoom: n)
	)
)

(instance ogreStuff of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(not enteredOgreKitchen)
				(== (self state?) 11)
				(> (ego y?) 122)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch ogreState
					(1
						(if enteredOgreKitchen
							(self changeState: 11)
						else
							(self changeState: 10)
						)
					)
					(2
						(self changeState: 20)
					)
					(3
						(self changeState: 30)
					)
				)
			)
			(10
				(if (== (frontDoor cel?) 0)
					(frontDoor setCycle: EndLoop setPri: 0)
				)
				(theMusic number: 5 loop: -1 play:)
				(self cue:)
			)
			(11
				((= aOgre (Actor new:))
					view: 250
					posn: 53 132
					setStep: 6 2
					init:
					setCycle: Walk
				)
				(User canInput: FALSE)
				(if (not enteredOgreKitchen)
					(aOgre setMotion: MoveTo 130 133)
				else
					(Face aOgre ego)
				)
			)
			(12
				(= local9 1)
				(aOgre
					;setAvoider: (Avoider new:)
					setCycle: Walk
					setMotion: Chase ego 15 self
				)
			)
			(13
				(User canControl: FALSE canInput: FALSE)
				(ego dispose:)
				(aOgre view: 78 setCycle: EndLoop self)
				(Print 49 46 #at -1 20 #draw)
				(theMusic number: 6 loop: 1 play:)
				(Print 49 47 #at -1 10)
			)
			(14
				(aOgre
					view: 79
					setPri: 9
					setCycle: Walk
					;setAvoider: Avoider
					setMotion: MoveTo 246 132 self
				)
			)
			(15
				(= dead TRUE)
			)
			(20
				(Print 49 48 #at -1 10)
				(ogreAwake state: 0)
				((= aOgre (Actor new:))
					illegalBits: 0
					view: 256
					loop: 2
					posn: 200 158
					setStep: 6 2
					setPri: 12
					init:
					setScript: ogreAwake
				)
			)
			(30
				(if (== prevRoomNum 51)
					(Print 49 49 #at -1 10)
				)
			)
		)
	)
)

(instance ogreAwake of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(not enteredOgreKitchen)
				(== (self state?) 1)
				(> (ego y?) 122)
				(not (& (ego onControl:) cGREEN))
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= ogreState ogreEATING)
					(if (== howFast slow)
						(= seconds 75)
					else
						(= seconds 60)
					)
				else
					(self cue:)
				)
			)
			(1
				(if (> (ego y?) 122)
					(return)
				)
			)
			(2
				(ego observeControl: cGREEN)
				(cond 
					((ego has: iMagicHen)
						(if (not wokeUpOgre)
							(Print 49 50 #time 4)
						)
					)
					((== ogreState ogreSLEEPING)
						(Print 49 51)
					)
				)
				(theMusic number: 5 loop: -1 play:)
				(aOgre cycleSpeed: 0 loop: 1 setCycle: EndLoop self)
			)
			(3
				(if (== howFast slow)
					(= seconds 3)
				else
					(self cue:)
				)
			)
			(4
				(= ogreState 4)
				(= local9 1)
				(aOgre
					view: 250
					posn: (aOgre x?) (- (aOgre y?) 3)
					;setAvoider: Avoider
					setCycle: Walk
					setMotion: Chase ego 15 self
				)
			)
			(5
				(User canControl: FALSE canInput: FALSE)
				(if (ego has: iMagicHen)
					(Print 49 52 #draw)
				else
					(Print 49 47 #draw)
				)
				(theMusic number: 6 loop: 1 play:)
				(ego dispose:)
				(aOgre view: 78 setCycle: EndLoop self)
			)
			(6
				(aOgre
					view: 79
					setCycle: Walk
					illegalBits: cWHITE
					setPri: -1
					;setAvoider: Avoider
					setMotion: MoveTo 246 132 self
				)
			)
			(7
				(= dead TRUE)
			)
		)
	)
)

(instance ogressChase of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 1)
				(theMusic number: 10 loop: -1 play:)
				(ogress
					illegalBits: cWHITE
					;setAvoider: Avoider
					ignoreActors: 1
					setCycle: Walk
					setMotion: Chase ego 15 self
				)
			)
			(1
				(HandsOff)
				(theMusic number: 11 loop: 1 play:)
				(ego hide:)
				(ogress
					view: 48
					cycleSpeed: 2
					cel: 255
					setCycle: EndLoop self
				)
			)
			(2
				(Print 49 53 #at -1 10 #draw)
				(= seconds 4)
			)
			(3
				(= dead TRUE)
			)
		)
	)
)

(instance henPecked of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hen
					view: 360
					posn: 225 129
					loop: 0
					show:
					setPri: 12
					ignoreActors: TRUE
					setCycle: Walk
					moveSpeed: 2
					cycleSpeed: 0
					setMotion: MoveTo 260 133 self
				)
			)
			(1
				(if (cast contains: hen)
					(hen
						loop: (+ (hen loop?) 2)
						setCycle: EndLoop self
					)
				)
			)
			(2
				(if (cast contains: hen)
					(hen
						setCycle: Walk
						setMotion: MoveTo
							(if (> (hen x?) 238)
								(Random 214 230)
							else
								(Random 240 262)
							)
							133
							self
					)
				)
			)
			(3
				(= state 0)
				(self cue:)
			)
		)
	)
)

(instance chaseEgo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 49 54 #at -1 10 #draw)
				(dog
					;setAvoider: (Avoider new:)
					setCycle: Forward
					ignoreActors: TRUE
					setMotion: Chase ego 15 self
				)
			)
			(1
				(User canInput: FALSE canControl: FALSE)
				(ego dispose:)
				(dog view: 87 loop: 0 setMotion: 0)
				(self cue:)
			)
			(2
				(dog loop: 1 setCycle: Forward)
				(theMusic number: 5 loop: -1 play:)
				(= seconds 3)
			)
			(3
				(dog loop: 3 cycleSpeed: 2 setCycle: EndLoop)
				(= seconds 4)
			)
			(4
				((= burp (Prop new:))
					view: 87
					loop: 4
					cel: 0
					setPri: 15
					posn: (- (dog x?) 17) (- (dog y?) 9)
					init:
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 2)
			)
			(5
				(burp dispose:)
				(Print 49 55 #draw #at -1 10)
				(= dead TRUE)
			)
		)
	)
)

(instance chewBone of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dog
					view: 350
					loop: 2
					ignoreActors: 0
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 2)
			)
			(1
				(dogsPlace
					top: (dog nsTop?)
					bottom: (+ (dog nsBottom?) 1)
					left: (- (dog nsLeft?) 1)
					right: (+ (dog nsRight?) 1)
					init:
				)
				(ego observeBlocks: dogsPlace)
			)
		)
	)
)

(instance dogsPlace of Block)

(instance catchBone of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Face ego dog)
				(throwSound number: 63 play:)
				(ego
					view: 15
					cycleSpeed: 1
					cel: 25
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(ego cel: 6 setCycle: EndLoop)
				((= bone (Actor new:))
					view: 541
					illegalBits: 0
					posn: (+ (ego x?) 3) (- (ego y?) 25)
					setLoop: 0
					setPri: 14
					setStep: 6 1
					setCycle: Forward
					setMotion: MoveTo (- (dog x?) 10) (- (dog y?) 11) self
					init:
				)
			)
			(2
				(Print 49 56 #width 290 #at -1 10 #dispose)
				(ego view: 2 cycleSpeed: 0 setCycle: Walk)
				(bone dispose:)
				(dog view: 350 loop: 3 setCycle: EndLoop self)
			)
			(3
				(dog
					view: 352
					setLoop: (if (< (dog x?) 155) 0 else 1)
					setCycle: Walk
					moveSpeed: 1
					setMotion: MoveTo 155 126 self
				)
			)
			(4
				(dog
					view: 350
					loop: 2
					ignoreActors: FALSE
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 8)
			)
			(5
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(HandsOn)
				(dog setScript: chewBone)
			)
		)
	)
)

(instance doorOpen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setMotion: MoveTo (+ (client x?) 20) (+ (client y?) 9) self
				)
			)
			(1
				(Face ego client)
				(client setCycle: EndLoop self)
			)
			(2
				(ego setMotion: MoveTo (ego x?) (+ (client y?) 2) self)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 51)
			)
		)
	)
)

(instance moveOnTheStairs of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego setStep: 2 2)
				(ego illegalBits: 0)
				(if (> (ego y?) 100)
					(ego setMotion: MoveTo 194 61 self)
				else
					(ego setMotion: MoveTo 97 123 self)
				)
			)
			(2
				(HandsOn)
				(ego illegalBits: cWHITE)
				(if (== ogreFrontDoorOpen FALSE)
					(ego observeControl: cYELLOW)
				)
				(ego setStep: 3 2)
				(= currentStatus egoNormal)
			)
		)
	)
)
