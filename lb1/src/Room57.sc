;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Avoider)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room57 0
)
(synonyms
	(diamond bag)
	(skeleton bone)
	(room crypt)
)

(local
	thisControl
	local1
	theCycles
	toX
	toY
	local5
	local6
	local7
	local8
	local9
	theCover
	local11
	barredMsg
	local13
	firstTime
	noGetInMsg
)
(procedure (LookRoom &tmp [str 250])
	(if (== tombDoorState 1)
		(Printf 57 33 57 34 @str)
	else
		(Printf 57 33 57 35 @str)
	)
)

(procedure (LookDoor &tmp [str 50])
	(switch thisControl
		(cGREEN
			(if (ego has: iPouch)
				(Print 57 24)
			else
				(Print 57 25)
				(Print 57 26)
			)
		)
		(cCYAN
			(Printf 57 36 57 37 @str)
		)
		(else 
			(Printf 57 36 57 38 @str)
		)
	)
)

(procedure (PutLanternHere)
	(lantern
		view: 27
		loop: 3
		cel: 0
		posn: 157 77
		setPri: 7
		stopUpd:
		init:
	)
	((inventory at: iLantern) moveTo: curRoomNum)
)

(instance Room57 of Room
	(properties
		picture 57
	)
	
	(method (init)
		(= horizon 0)
		(super init:)
		(addToPics add: skeleton casket skeletons doit:)
		(self setFeatures: Box Window1 Window2)
		(LoadMany VIEW 27 28 29 30)
		(LoadMany SCRIPT 985 991)
		(LoadMany SOUND 60 71 122 123 124)
		(if (== prevRoomNum 2)
			(= local13 1)
			(ego view: 0 posn: 43 148 init:)
		)
		(lid
			view: 157
			loop: 0
			posn: 228 84
			setPri: 8
			ignoreActors: TRUE
			init:
		)
		(if (== ((inventory at: iLantern) owner?) curRoomNum)
			(PutLanternHere)
		)
		(if (not (ego has: iPouch))
			(pouch setPri: 1 stopUpd: init:)
		)
		(marysCover
			view: 157
			loop: 2
			ignoreActors: TRUE
			stopUpd:
			init:
		)
		(if (& global169 $0002)
			(marysCover cel: (- (NumCels marysCover) 1) posn: 32 87)
		else
			(marysCover loop: 1 cel: 0 posn: 46 87)
		)
		(rubysCover
			view: 157
			loop: 2
			ignoreActors: TRUE
			stopUpd:
			init:
		)
		(if (& global169 $0004)
			(rubysCover
				cel: (- (NumCels rubysCover) 1)
				posn: 116 69
			)
		else
			(rubysCover loop: 1 cel: 1 posn: 130 70)
		)
		(tomsCover
			view: 157
			loop: 3
			ignoreActors: TRUE
			stopUpd:
			init:
		)
		(if (& global169 $0008)
			(tomsCover cel: (- (NumCels tomsCover) 1) posn: 192 71)
		else
			(tomsCover loop: 1 cel: 2 posn: 177 71)
		)
		(claudesCover
			view: 157
			loop: 3
			ignoreActors: TRUE
			stopUpd:
			init:
		)
		(if (& global169 $0010)
			(claudesCover
				cel: (- (NumCels claudesCover) 1)
				posn: 281 91
			)
		else
			(claudesCover loop: 1 cel: 3 posn: 266 91)
		)
		(skull
			view: 157
			illegalBits: 0
			ignoreActors: TRUE
			priority: 1
		)
		(if (& global169 $0200)
			(skull loop: 6)
			(skull cel: (- (NumCels skull) 1) posn: 75 117)
		else
			(skull setLoop: 5 cel: 0 posn: 53 79)
		)
		(skull stopUpd: init:)
		(if (FirstEntry)
			(= firstTime 1)
			(myMusic number: 71 loop: 1 play:)
			(lid cel: 0 cycleSpeed: 4 setCycle: EndLoop self)
		else
			(ego init:)
			(lid cel: (- (NumCels lid) 1) stopUpd:)
			(if (!= prevRoomNum 2)
				(self setScript: GettingOut)
			)
		)
	)
	
	(method (doit)
		(cond 
			((== tombDoorState 1)
				(if (& (ego onControl: origin) cBLUE)
					(curRoom newRoom: 2)
				)
			)
			((& (ego onControl: origin) cLBLUE)
				(if (not barredMsg)
					(= barredMsg TRUE)
					(Print 57 0)
				)
			)
			(else
				(= barredMsg FALSE)
			)
		)
		(if (and (& (ego onControl: origin) cRED) local13)
			(cond 
				(lanternIsLit
					(self setScript: GettingIn)
				)
				((not noGetInMsg)
					(= noGetInMsg TRUE)
					(Print 57 1)
				)
			)
		)
		(if (& (ego onControl: origin) cBLACK)
			(= noGetInMsg FALSE)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				((Said '(examine<in),get,open/casket>')
					(if (& global169 $0008)
						(Print 57 2)
					else
						(DontSee)
					)
					(event claimed: TRUE)
				)
				((or (Said 'latch,bar/*') (Said 'lower/bar'))
					(Print 57 3)
				)
				(
					(and
						(not (ego has: iCrowbar))
						(or
							(Said '*/crowbar')
							(Said '*/*/crowbar')
							(Said '*<use<crowbar')
						)
					)
					(DontHave)
				)
				(
				(or (Said 'examine,read/nameplate') (Said 'read/vault'))
					(cond 
						((& (ego onControl: FALSE) cBLUE)
							(Print 57 4)
						)
						((& (ego onControl: FALSE) cGREEN)
							(Print 57 5)
						)
						((& (ego onControl: FALSE) cCYAN)
							(Print 57 6)
						)
						((& (ego onControl: FALSE) cRED)
							(Print 57 7)
						)
						(else
							(NotClose)
						)
					)
				)
				(
					(or
						(Said '/vault,(door<vault)>')
						(and (Said '/door>') (Said '/vault>'))
						(Said '//vault,(door<vault)>')
						(and (Said '//door>') (Said '//vault>'))
					)
					(= thisControl cBLUE)
					(while (<= thisControl cRED)
						(if (& (ego onControl: origin) thisControl)
							(= temp0 2)
							(if theInvItem
								(if (not haveInvItem) (return))
								(= temp0 (!= whichItem iCrowbar))
							)
							(if (Said 'examine>')
								(if (not (& global169 thisControl))
									(Print 57 8)
									(event claimed: TRUE)
									(break)
								)
								(if (Said '<in')
									(LookDoor)
									(break)
								)
								(Print 57 9)
								(event claimed: TRUE)
								(break)
							)
							(if
								(or
									(Said '(break,force)[<(open,up)]//cane')
									(Said '(break,lift,force)<use<cane')
									(Said 'open//cane')
								)
								(if (ego has: iCane)
									(Print 57 10)
									(break)
								)
								(DontHave)
								(break)
							)
							(if
								(or
									(Said '(break,force)[<(open,up)]//poker')
									(Said '(break,lift,force)<use<poker')
									(Said 'open//poker')
								)
								(if (ego has: iPoker)
									(Print 57 11)
									(break)
								)
								(DontHave)
								(break)
							)
							(if
								(and
									(!= temp0 1)
									(not (& global169 thisControl))
									(or
										(Said '(break,force)[<(open,up)]//crowbar')
										(Said '(break,lift,force)<use<crowbar')
										(Said 'open//crowbar')
										(Said 'open<use<crowbar')
									)
								)
								(if (ego has: iCrowbar)
									(self setScript: OpenVault)
									(break)
								)
								(Print 57 12)
								(break)
							)
							(if (Said 'break,force,open')
								(if (& global169 thisControl)
									(AlreadyOpen)
									(break)
								)
								(if (not (& global169 (<< thisControl cCYAN)))
									(Print 57 13)
									(break)
								)
								(self setScript: OpenVault)
								(break)
							)
							(if (Said 'close')
								(if (not (& global169 thisControl))
									(AlreadyClosed)
									(break)
								)
								(ego illegalBits: 0)
								(self setScript: CloseVault)
								(break)
							)
							(if (Said 'unbar')
								(if (not (& global169 thisControl))
									(Print 57 14)
									(break)
								)
								(AlreadyOpen)
								(break)
							)
						)
						(= thisControl (<< thisControl $0001))
					)
					(if (> thisControl cRED)
						(NotClose)
						(event claimed: TRUE)
					)
				)
				(
					(or
						(Said '/bar,door[<room]>')
						(and (Said '/door>') (Said '/room>'))
					)
					(cond 
						((Said 'examine')
							(if (== tombDoorState 0)
								(Print 57 0)
							else
								(Print 57 15)
							)
						)
						((Said 'open')
							(if (== tombDoorState 0)
								(Print 57 0)
							else
								(Print 57 15)
							)
						)
						((Said 'unbar,unbar,detach,lift,lift,move')
							(if
								(or
									(& (ego onControl: FALSE) cLBLUE)
									(& (ego onControl: FALSE) cBROWN)
								)
								(if (== tombDoorState 0)
									(= tombDoorState 1)
									(Print 57 16)
								else
									(Print 57 17)
								)
							else
								(NotClose)
							)
						)
					)
				)
				((Said 'examine>')
					(cond 
						((Said '[<around,at][/room]')
							(LookRoom)
						)
						((Said '/stair')
							(Print 57 18)
						)
						((Said '/wall')
							(Print 57 19)
						)
						((or (Said '/dirt') (Said '<down'))
							(Print 57 20)
						)
						((or (Said '/ceiling') (Said '<up'))
							(Print 57 21)
						)
						((Said '/frances,(crouton<frances)')
							(cond 
								((not (& global169 $0002))
									(Print 57 22)
								)
								((not (& (ego onControl: origin) cBLUE))
									(NotClose)
								)
								(else
									(Print 57 23)
								)
							)
						)
						((Said '/s,(crouton<s)')
							(cond 
								((not (& global169 $0008))
									(Print 57 22)
								)
								((not (& (ego onControl: origin) cCYAN))
									(NotClose)
								)
								(else
									(Print 57 23)
								)
							)
						)
						((Said '/claude,(crouton<claude)')
							(cond 
								((not (& global169 $0010))
									(Print 57 22)
								)
								((not (& (ego onControl: origin) cRED))
									(NotClose)
								)
								(else
									(Print 57 23)
								)
							)
						)
						((Said '/ruby,(crouton<ruby)')
							(cond 
								((not (& global169 $0004))
									(Print 57 22)
								)
								((not (& (ego onControl: origin) cGREEN))
									(NotClose)
								)
								((ego has: iPouch)
									(Print 57 24)
								)
								(else
									(Print 57 25)
									(myMusic number: 60 loop: 1 play:)
									(Print 57 26)
								)
							)
						)
						((Said '/skeleton,casket>')
							(= thisControl cBLUE)
							(while (<= thisControl cRED)
								(if
									(and
										(& (ego onControl: origin) thisControl)
										(& global169 thisControl)
									)
									(if (== thisControl cGREEN)
										(DontSee)
										(break)
									)
									(if (Said '/skeleton')
										(if (== thisControl cCYAN)
											(DontSee)
											(break)
										)
										(Print 57 23)
										(break)
									)
									(if (!= thisControl cCYAN)
										(DontSee)
										(break)
									)
									(Print 57 27)
									(break)
								)
								(= thisControl (<< thisControl $0001))
							)
							(if (> thisControl 16)
								(if global169
									(if
										(or
											(and (Said '/skeleton') (& global169 $0012))
											(and (Said '/casket') (& global169 $0008))
										)
										(NotClose)
									else
										(DontSee)
									)
								else
									(DontSee)
								)
							)
							(event claimed: TRUE)
						)
					)
				)
				((Said '(move,get)>')
					(cond 
						((Said '/skeleton,casket>')
							(if
								(or
									(and (Said '/skeleton') (& global169 $0012))
									(and (Said '/casket') (& global169 $0008))
								)
								(Print 57 2)
							else
								(DontSee)
								(event claimed: TRUE)
							)
						)
						((Said '/lantern')
							(if (ego has: iLantern)
								(AlreadyTook)
							else
								(HandsOff)
								(ego
									setAvoider: (Avoider new:)
									setMotion: MoveTo 144 112 self
								)
							)
						)
					)
				)
				((Said 'open/window')
					(Print 57 28)
				)
				((Said 'break/window')
					(Print 57 29)
				)
				((Said '(close,cover)>')
					(if
						(or
							(Said '/sarcophagus,(lid<sarcophagus)')
							(and (Said '/lid>') (Said '/sarcophagus'))
						)
						(Print 57 30)
					)
				)
				((Said 'extinguish,extinguish,(rotate<off)')
					(Print 57 31)
				)
			)
		)
	)
	
	(method (cue)
		(if (ego has: iLantern)
			(lid stopUpd:)
			(ego init:)
			(self setScript: GettingOut)
		else
			(lantern hide:)
			(ego loop: 0 setAvoider: 0)
			((inventory at: iLantern) moveTo: ego)
			(HandsOn)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance OpenVault of Script
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (& global169 $0200))
				(== state 4)
				(== (marysCover cel?) 1)
				(== (skull script?) 0)
			)
			(skull setScript: RollSkull)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 2)
				(if (not (& global169 (= local1 thisControl)))
					(HandsOff)
					(if (not (& global169 (<< local1 $0008)))
						(Print 57 32 #at 90 110 #mode teJustCenter)
						(= theCycles 10)
					else
						(Ok)
						(= theCycles 3)
					)
					(switch local1
						(2
							(= toX 78)
							(= toY 108)
							(= local5 0)
							(= theCover marysCover)
						)
						(4
							(= toX 99)
							(= toY 103)
							(= local5 1)
							(= theCover rubysCover)
						)
						(8
							(= toX 209)
							(= toY 104)
							(= local5 0)
							(= theCover tomsCover)
						)
						(16
							(= toX 234)
							(= toY 111)
							(= local5 1)
							(= theCover claudesCover)
						)
					)
					(ego illegalBits: 0 setMotion: MoveTo toX toY self)
				else
					(AlreadyOpen)
					(client setScript: 0)
				)
			)
			(1
				(ego view: 29 loop: local5 cel: 0 setCycle: EndLoop self)
			)
			(2
				(ego loop: (+ local5 2) cel: 0 setCycle: EndLoop self)
			)
			(3
				(ego loop: (+ local5 4) cel: 0 setCycle: Forward)
				(= cycles theCycles)
			)
			(4
				(switch local1
					(2
						(theCover loop: 2 posn: 32 87)
					)
					(4
						(theCover loop: 2 posn: 116 69)
					)
					(8
						(theCover loop: 3 posn: 192 71)
					)
					(16
						(theCover loop: 3 posn: 281 91)
					)
				)
				(theCover cel: 0 setCycle: EndLoop)
				(myMusic number: 123 loop: 1 play:)
				(ego loop: (+ local5 2))
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(5
				(ego loop: 0)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(6
				(ego
					view: 0
					loop: 2
					cel: 6
					setCycle: Walk
					illegalBits: cWHITE
				)
				(= global169 (| global169 (<< local1 $0008) local1))
				(cls)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance CloseVault of Script
	
	(method (doit &tmp egoCel)
		(super doit:)
		(if (== state 2)
			(cond 
				((and (> (= egoCel (ego cel?)) 2) (< egoCel 6))
					(theCover cel: (- 5 egoCel))
				)
				((== egoCel 6)
					(switch local1
						(2
							(= local6 46)
							(= local7 87)
							(= local9 0)
						)
						(4
							(= local6 130)
							(= local7 70)
							(= local9 1)
						)
						(8
							(= local6 177)
							(= local7 71)
							(= local9 2)
						)
						(16
							(= local6 266)
							(= local7 91)
							(= local9 3)
						)
					)
					(theCover loop: 1 cel: local9 posn: local6 local7)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (& global169 thisControl)
					(HandsOff)
					(= local1 thisControl)
					(= global169 (& global169 (~ thisControl)))
					(Ok)
					(switch local1
						(2
							(= local6 53)
							(= local7 115)
							(= local8 0)
							(= theCover marysCover)
							(= local11 15)
						)
						(4
							(= local6 146)
							(= local7 99)
							(= local8 0)
							(= theCover rubysCover)
							(= local11 15)
						)
						(8
							(= local6 169)
							(= local7 100)
							(= local8 1)
							(= theCover tomsCover)
							(= local11 -15)
						)
						(16
							(= local6 260)
							(= local7 121)
							(= local8 1)
							(= theCover claudesCover)
							(= local11 -15)
						)
					)
					(ego setMotion: MoveTo local6 local7 self)
				else
					(ego illegalBits: cWHITE)
					(AlreadyClosed)
					(client setScript: 0)
				)
			)
			(1
				(ego view: 0 loop: 3 cel: 0)
				(= cycles 1)
			)
			(2
				(ego view: 30 loop: local8 cel: 0 setCycle: EndLoop self)
				(myMusic number: 123 loop: 1 play:)
			)
			(3
				(ego
					view: 0
					loop: 2
					cel: 6
					posn: (+ (ego x?) local11) (+ (ego y?) 3)
					setCycle: Walk
					illegalBits: cWHITE
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance RollSkull of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(skull
					startUpd:
					setPri: 15
					setStep: 6 4
					setCycle: Forward
					moveSpeed: 0
					cycleSpeed: 0
					setMotion: MoveTo 75 117 self
				)
			)
			(1
				(myMusic number: 122 loop: 1 play:)
				(skull setMotion: JumpTo 90 117 self)
			)
			(2
				(myMusic number: 124 loop: 1 play:)
				(skull loop: 6 cel: 0 setPri: -1 setCycle: EndLoop self)
			)
			(3
				(skull stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance GettingIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local13 0)
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 176 109 self)
			)
			(1
				(ego
					view: 28
					loop: 0
					cel: 0
					posn: 176 107
					setPri: 8
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(ego view: 27 posn: 167 81)
				(if (ego has: 2)
					(self cue:)
				else
					(ego loop: 0 cycleSpeed: 1)
					(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
					(ego get: 2)
					(lantern hide:)
				)
			)
			(3
				(ego loop: 4 cycleSpeed: 2)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(4
				(ego
					setPri: -1
					cycleSpeed: 0
					illegalBits: cWHITE
					setCycle: Walk
				)
				(HandsOn)
				(client setScript: 0)
				(curRoom newRoom: 56)
			)
		)
	)
)

(instance GettingOut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local13 0)
				(HandsOff)
				(ego
					view: 27
					loop: 4
					cel: 0
					illegalBits: 0
					posn: 167 81
					setPri: 8
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(1
				(ego loop: 0 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(2
				(ego view: 28 loop: 0 posn: 176 107 cycleSpeed: 2)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
				(PutLanternHere)
			)
			(3
				(ego
					view: 0
					loop: 3
					cel: 0
					posn: 176 109
					setPri: -1
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 155 124 self
				)
			)
			(4
				(ego illegalBits: cWHITE)
				(HandsOn)
				(if firstTime (LookRoom))
				(= local13 1)
				(= firstTime 0)
				(client setScript: 0)
			)
		)
	)
)

(instance lid of Prop)

(instance marysCover of Prop
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 57 4)
		)
	)
)

(instance rubysCover of Prop
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 57 5)
		)
	)
)

(instance tomsCover of Prop
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 57 6)
		)
	)
)

(instance claudesCover of Prop
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 57 7)
		)
	)
)

(instance pouch of Prop
	(properties
		y 63
		x 124
		view 157
		loop 4
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'get/diamond')
				(cond 
					((& (ego onControl: FALSE) cGREEN)
						(= gotItem TRUE)
						(ego get: iPouch)
						(pouch dispose:)
						(Print 57 39)
					)
					((& global169 $0004)
						(NotClose)
					)
					(else
						(DontSee)
					)
				)
			)
			((MousedOn self event shiftDown)
				(event claimed: TRUE)
				(Print 57 26)
			)
		)
	)
)

(instance lantern of Prop)

(instance skull of Actor)

(instance skeleton of PicView
	(properties
		y 81
		x 40
		view 157
		loop 4
		priority 1
	)
)

(instance casket of PicView
	(properties
		y 66
		x 182
		view 157
		loop 4
		cel 2
		priority 1
	)
)

(instance skeletons of PicView
	(properties
		y 87
		x 272
		view 157
		loop 4
		cel 3
		priority 1
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 13
		nsLeft 72
		nsBottom 59
		nsRight 105
	)
	
	(method (handleEvent event)
		(if
			(or
				(Said 'examine/window')
				(Said 'examine/glass<stained')
				(MousedOn self event shiftDown)
			)
			(Print 57 40)
			(event claimed: TRUE)
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 12
		nsLeft 203
		nsBottom 61
		nsRight 240
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(Print 57 40)
			(event claimed: TRUE)
		)
	)
)

(instance Box of RFeature
	(properties
		nsTop 75
		nsLeft 153
		nsBottom 119
		nsRight 28
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event shiftDown)
				(Said 'examine/sarcophagus')
			)
			(Print 57 41)
			(event claimed: TRUE)
		)
	)
)

(instance myMusic of Sound)
