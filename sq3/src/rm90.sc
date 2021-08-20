;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm90 0
)

(local
	goingWhere
	theHallDoor
	theDoorScript
	local3
	securityDoor
	local5
	local6
	local7
	theState
	local9
)
(instance rm90 of Room
	(properties
		picture 90
		style HSHUTTER
	)
	
	(method (init)
		(StatusLine enable:)
		(HandsOn)
		(Load VIEW 105)
		(Load VIEW 110)
		(Load VIEW 109)
		(Load VIEW 111)
		(Load SOUND 14)
		(Load SOUND 49)
		(Load SOUND 57)
		(Load SOUND 67)
		(Load SOUND 60)
		(Load SOUND 74)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event &tmp item)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (and (< (ego x?) 84) (== local3 90))
					(cond 
						(
							(Said
								'look[/area,partition,left,right,up,down,ceiling,deck]'
							)
							(Print 90 0)
						)
						((Said 'look/door') (Print 90 1))
						((Said 'look,explore/area,closet') (ego setScript: searchScript))
						(
						(Said 'get,wear,drop,change,switch,explore/attire')
							(if (ego has: iCoveralls)
								(Print 90 2)
							else
								(Print 90 3)
								(Print 90 4)
								(= item 0)
								(while (<= item 16)
									(if (ego has: item) (ego put: item -1))
									(++ item)
								)
								(ego view: 109 get: iCoveralls get: iVaporizer)
								(theGame changeScore: 5)
							)
						)
						((Said 'disembark,disembark[/area,closet]') (Print 90 5))
					)
				)
				(cond 
					((Said 'get/life[<a]')
						(if debugging
							(ego view: 109 get: 11 12 13 15)
						else
							(event claimed: FALSE)
						)
					)
					(
						(Said
							'look[/area,corridor,deck,dirt,ceiling,partition,up,down,left,right]'
						)
						(cond 
							((and (<= 114 (ego x?)) (<= (ego x?) 203))
								(if (or (== (ego loop?) 3) (== (ego loop?) 2))
									(if (== goingWhere {some})
										(= goingWhere {no})
									else
										(= goingWhere {some})
									)
									(Printf 90 6 goingWhere)
								else
									(Print 90 7)
								)
							)
							(
								(or
									(and (== (ego loop?) 1) (> (ego x?) 114))
									(and (== (ego loop?) 0) (< (ego x?) 203))
								)
								(Print 90 8)
							)
							(else (Print 90 9))
						)
					)
					((Said 'look/closet') (Print 90 10))
					((Said 'look/door')
						(if
							(or
								(== theHallDoor wHallDoor)
								(== theHallDoor eHallDoor)
							)
							(if securityDoor (Print 90 11) else (Print 90 12))
						else
							(Print 90 13)
						)
					)
					(
					(or (Said 'look/mrgarbage') (Said 'look/garbage<mr'))
						(if (ego has: iVaporizer)
							((inventory at: iVaporizer) showSelf:)
						else
							(event claimed: 0)
						)
					)
					((Said 'remove/attire[<janitor]')
						(if (ego has: iCoveralls)
							(Print 90 14)
						else
							(event claimed: FALSE)
						)
					)
					(
						(or
							(Said 'blast')
							(Said 'use/mrgarbage')
							(Said 'use/garbage<mr')
						)
						(if (ego has: iVaporizer) (Print 90 15) else (Print 90 16))
					)
					((Said 'find,get/card') (Print 90 17))
					(
					(Said 'look/device,keylock,latch,card,girder,device')
						(if
						(and (== theHallDoor eHallDoor) (== local3 95))
							(Print 90 18)
						else
							(event claimed: FALSE)
						)
					)
					((Said '*/device,keylock,latch,girder,device')
						(if
						(and (== theHallDoor eHallDoor) (== local3 95))
							(Print 90 19)
						else
							(event claimed: FALSE)
						)
					)
					((Said 'open/door[<corridor]')
						(if
						(and securityDoor (<= 186 (ego x?)) (<= (ego x?) 203))
							(Print 90 20)
						else
							(Print 90 21)
						)
					)
					((Said 'close,close/door[<corridor]') (Print 90 22))
					(
						(or
							(Said 'drop,use,insert,enter/card,key')
							(Said 'unlock/door[<corridor]')
						)
						(cond 
							(
								(not
									(if (and securityDoor (<= 186 (ego x?))) (<= (ego x?) 203))
								)
								(Print 90 23)
							)
							((not (ego has: iKeycard)) (Print 90 24))
							((not (User canControl:)) (Print 90 25))
							((not (== (ego loop?) 0)) (Print 90 26))
							(
							(or (<= (scanner y?) 90) (>= (scanner y?) 95)) (Print 90 27))
							(else (ego setScript: scanScript))
						)
					)
					((Said '*/door') (if securityDoor (Print 90 28) else (Print 90 29)))
					((Said 'look/crack,crack') (Print 90 30))
					((Said 'explore,look/pocket') (if (ego has: iCoveralls) (Print 90 31) else (Print 90 32)))
					(
						(or
							(Said 'copy<use')
							(Said
								'drop,hold,display,use,position,place/original,copy'
							)
						)
						(if (ego has: iElmoPictureCopy)
							(if (== (ego view?) 105)
								(ego setLoop: 1 setCel: 0 setCycle: EndLoop)
								(User canInput: 0)
							else
								(Print 90 33)
							)
						else
							(Print 90 34)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(timers eachElementDo: #dispose 84)
		(super newRoom: newRoomNumber)
	)
)

(instance searchScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(searchSound prevSignal: 0)
				(searchSound play:)
				(= cycles 1)
			)
			(1
				(if (!= (searchSound prevSignal?) -1)
					(-- state)
					(Timer setCycle: self 3)
				else
					(HandsOn)
					(if (InRoom iCoveralls)
						(Print 90 35)
					else
						(Print 90 36)
					)
				)
			)
			(else  (self init:))
		)
	)
)

(instance scanScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(hallSound stop:)
				(ego
					view: 105
					setLoop: 0
					setCel: 0
					x: 202
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(1
				(keycardSound play:)
				(Timer setReal: self 2)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(Print 90 37)
				(Print 90 38)
				(User canInput: TRUE)
				(ego setPri: 13)
				(Timer setReal: self 3)
			)
			(4
				(User canInput: FALSE)
				(if (== (ego loop?) 1)
					(Timer setReal: self 3)
				else
					(= cycles 1)
				)
			)
			(5
				(face init:)
				(scanner setLoop: 6)
				(= local9 6)
				(= cycles 1)
			)
			(6
				(scannerSound play:)
				(face setCel: 0 setCycle: EndLoop)
				(scanner setCel: 0 setCycle: EndLoop self)
			)
			(7
				(if (-- local9)
					(self changeState: 6)
				else
					(= cycles 1)
				)
			)
			(8
				(face dispose:)
				(scanner setLoop: 5 setCel: 4 setCycle: 0 stopUpd:)
				(Timer setReal: self 2)
			)
			(9
				(if (== (ego loop?) 1)
					(= securityDoor FALSE)
					(ego setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(10
				(ego
					view: 109
					setLoop: -1
					setCel: -1
					setDirection: 3
					setMotion: 0
					setCycle: Walk
				)
				(Timer setCycle: self 3)
			)
			(11
				(if securityDoor (Print 90 39 #at -1 20 #width 280))
				(hallSound play:)
				(HandsOn)
			)
			(12 (self init:))
		)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(ego
			view: (if (ego has: iVaporizer) 109 else 110)
			setPri: 13
			setCel: -1
			setLoop: -1
			setCycle: Walk
			setStep: 3 1
			x: (if (== prevRoomNum 86) 65 else 262)
			y: (if (== prevRoomNum 86) 123 else 119)
			setDirection: (if (== prevRoomNum 86) 3 else 7)
			observeBlocks: egoArea
			init:
		)
		(wHallDoor init:)
		(eHallDoor init:)
		(bPanelLine init:)
		(tPanelLine init:)
		(egoArea init:)
		(scanner init:)
		(hallSound init:)
		(doorSound init:)
		(ouchSound init:)
		(scannerSound init:)
		(keycardSound init:)
		(searchSound init:)
		(hallSound play:)
		(switch prevRoomNum
			(91
				(self changeState: (= state 5))
			)
			(95
				(theGame changeScore: -20)
				(self changeState: (= state 13))
			)
			(86
				(self changeState: (= state 0))
			)
			(else 
				(ego posn: 65 123 setDirection: 3)
				(self changeState: (= state 0))
			)
		)
	)
	
	(method (doit)
		(switch (ego loop?)
			(0
				(cond 
					((>= (ego x?) 203)
						(cond 
							(
								(and
									(> (ego y?) (+ 119 (ego yStep?)))
									(< (ego x?) (+ 203 (ego xStep?)))
								)
								(if (== local6 2)
									(ego posn: 203 119)
								else
									(ego posn: 202 130 setDirection: 0)
									(ShakeScreen 4 2)
									(ouchSound play:)
								)
							)
							((> (ego x?) 262)
								(if (== local3 91)
									(ego setDirection: 1)
									(curRoom newRoom: 91)
								else
									(curRoom newRoom: 95)
									(theGame changeScore: 20)
								)
							)
							(else (ego y: 119))
						)
					)
					((>= (ego x?) 114)
						(if (== (ego y?) 123)
							(ego y: 130)
						else
							(ego y: 130)
							(cond 
								((and (> (ego x?) 131) (== local5 0) local7)
									(ego setDirection: local7)
									(= local7 0)
									(User canControl: 1)
								)
								((== local6 0)
									(cond 
										((== (eDoorScript state?) 3) (ego setScript: eDoorScript))
										((== (eDoorScript state?) 5) (eDoorScript changeState: (- (eDoorScript state?) 1)))
									)
								)
							)
						)
					)
					(else (ego y: 123))
				)
			)
			(1
				(cond 
					((<= (ego x?) 114)
						(cond 
							(
								(and
									(> (ego y?) (+ 123 (ego yStep?)))
									(> (ego x?) (- 114 (ego xStep?)))
								)
								(if (== local5 2)
									(ego posn: 114 123)
								else
									(ego posn: 115 130 setDirection: 0)
									(ShakeScreen 4 2)
									(ouchSound play:)
								)
							)
							((< (ego x?) 65)
								(if (== local3 90)
									(ego posn: 65 123)
								else
									(curRoom newRoom: 86)
								)
							)
							(else (ego y: 123))
						)
					)
					((<= (ego x?) 203)
						(if (== (ego y?) 119)
							(ego y: 130)
						else
							(ego y: 130)
							(cond 
								(
								(and (< (ego x?) 186) (== local6 0) (!= local7 0))
									(ego setDirection: local7)
									(= local7 0)
									(User canControl: 1)
								)
								((== local5 0)
									(cond 
										((== (wDoorScript state?) 2) (ego setScript: wDoorScript))
										((== (wDoorScript state?) 4) (wDoorScript changeState: (- (wDoorScript state?) 1)))
									)
								)
							)
						)
					)
					(else (ego y: 119))
				)
			)
			(2
				(if (and (< 131 (ego x?)) (< (ego x?) 186))
					(if
					(and (!= (ego y?) 130) (== local5 0) (== local6 0))
						(ego posn: (ego x?) 130)
						(if (== (theDoorScript state?) -1)
							(rmScript changeState: (- (rmScript state?) 1))
						)
						(theDoorScript
							changeState: (- (theDoorScript state?) 1)
						)
					)
				else
					(User canControl: FALSE)
					(= local7 5)
					(ego setDirection: (if (<= (ego x?) 131) 3 else 7))
				)
			)
			(3
				(if (and (< 131 (ego x?)) (< (ego x?) 186))
					(if
					(and (!= (ego y?) 130) (== local5 0) (== local6 0))
						(ego posn: (ego x?) 130)
						(if
						(== (theDoorScript state?) (theDoorScript start?))
							(rmScript cue:)
						)
						(theDoorScript
							changeState: (+ (theDoorScript state?) 1)
						)
					)
				else
					(User canControl: 0)
					(= local7 1)
					(ego setDirection: (if (<= (ego x?) 131) 3 else 7))
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(= theState state)
		(switch (= state newState)
			(0
				(= theHallDoor wHallDoor)
				(= local3 86)
				(= theDoorScript wDoorScript)
			)
			(1
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
			)
			(2)
			(3)
			(4
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
			)
			(5
				(= theHallDoor eHallDoor)
				(= local3 91)
				(= theDoorScript eDoorScript)
			)
			(6
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
			)
			(7
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
			)
			(8
				(= theHallDoor wHallDoor)
				(= local3 90)
				(= theDoorScript wDoorScript)
			)
			(9
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
			)
			(10)
			(11)
			(12
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
				(= securityDoor 0)
			)
			(13
				(= theHallDoor eHallDoor)
				(= local3 95)
				(= theDoorScript eDoorScript)
				(if (< (ego x?) 203) (= securityDoor 1))
			)
			(14
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
				(= securityDoor 0)
			)
			(15
				(= theHallDoor bPanelLine)
				(= theDoorScript panelScript)
			)
			(16
				(= state -1)
				(self changeState: 0)
			)
			(else 
				(= state 16)
				(self changeState: 15)
			)
		)
		(cond 
			((> state theState) (theDoorScript state: -1))
			((< state theState) (theDoorScript state: (theDoorScript start?)))
			(else
				(theDoorScript
					changeState: (- (theHallDoor lastCel:) 1)
				)
			)
		)
	)
)

(instance wDoorScript of Script
	(properties
		start 5
	)
	
	(method (changeState newState)
		(wHallDoor posn: 0 0 setCel: newState setPri: 6)
		(switch (= state newState)
			(0 (wHallDoor posn: 119 123))
			(1 (wHallDoor posn: 113 124))
			(2 (wHallDoor posn: 110 127))
			(3 (wHallDoor posn: 104 130))
			(4
				(wHallDoor posn: 93 135 setPri: 14)
			)
			(5 (rmScript cue:))
			(else 
				(rmScript changeState: (- (rmScript state?) 1))
			)
		)
	)
)

(instance eDoorScript of Script
	(properties
		start 6
	)
	
	(method (changeState newState)
		(eHallDoor posn: 0 0 setCel: newState setPri: 5)
		(if (== local3 95)
			(scanner posn: 0 0 setCel: newState setPri: 6)
		)
		(switch (= state newState)
			(0
				(eHallDoor posn: 134 110)
				(if (== local3 95) (scanner posn: 151 85))
			)
			(1
				(eHallDoor posn: 158 116)
				(if (== local3 95) (scanner posn: 176 86))
			)
			(2
				(eHallDoor posn: 177 120)
				(if (== local3 95) (scanner posn: 197 87))
			)
			(3
				(eHallDoor posn: 203 131)
				(if (== local3 95) (scanner posn: 221 89))
			)
			(4
				(eHallDoor posn: 213 136)
				(if (== local3 95) (scanner posn: 229 91 setPri: 14))
			)
			(5
				(eHallDoor posn: 226 131 setPri: 14)
			)
			(6 (rmScript cue:))
			(else 
				(rmScript changeState: (- (rmScript state?) 1))
			)
		)
	)
)

(instance panelScript of Script
	(properties
		start 8
	)
	
	(method (changeState newState)
		(bPanelLine posn: 0 0)
		(tPanelLine posn: 0 0)
		(switch (= state newState)
			(0)
			(1
				(bPanelLine posn: 158 115)
				(tPanelLine posn: 158 50)
			)
			(2
				(bPanelLine posn: 158 119)
				(tPanelLine posn: 158 47)
			)
			(3
				(bPanelLine posn: 158 123)
				(tPanelLine posn: 158 43)
			)
			(4
				(bPanelLine posn: 158 127)
				(tPanelLine posn: 158 40)
			)
			(5
				(bPanelLine posn: 158 133)
				(tPanelLine posn: 158 37)
			)
			(6
				(bPanelLine posn: 158 135)
				(tPanelLine posn: 158 34)
			)
			(7)
			(8 (rmScript cue:))
			(else 
				(rmScript changeState: (- (rmScript state?) 1))
			)
		)
	)
)

(instance wHallDoor of Prop
	(properties
		view 111
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: 1 setLoop: 0 posn: 0 0 setPri: 6)
		(= local5 0)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (<= 75 (ego x?)) (<= (ego x?) 131))
				(if (and (== (self loop?) 0) (== (self cel?) 3))
					(self setLoop: 1 setCel: 0)
					(= local5 1)
				)
				(if (== local5 1)
					(= local5 3)
					(doorSound play:)
					(self setCycle: EndLoop self)
				)
			)
			((or (== local5 3) (== local5 2)) (= local5 1) (doorSound play:) (self setCycle: BegLoop self))
		)
	)
	
	(method (cue)
		(if (== local5 1)
			(= local5 0)
			(wHallDoor setLoop: 0 setCel: 3 startUpd:)
		else
			(= local5 2)
			(wHallDoor stopUpd:)
		)
	)
)

(instance eHallDoor of Prop
	(properties
		view 111
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: 1 setLoop: 2 posn: 0 0 setPri: 5)
		(= local6 0)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (<= 186 (ego x?)) (<= (ego x?) 252))
				(if
					(and
						(== (self loop?) 2)
						(== (self cel?) 4)
						(not securityDoor)
					)
					(= local6 1)
					(self setLoop: 3 setCel: 0)
				)
				(if (== local6 1)
					(= local6 3)
					(doorSound play:)
					(self setCycle: EndLoop self)
				)
			)
			((or (== local6 3) (== local6 2)) (= local6 1) (doorSound play:) (self setCycle: BegLoop self))
		)
	)
	
	(method (cue)
		(if (== local6 1)
			(= local6 0)
			(self setLoop: 2 setCel: 4 startUpd:)
			(if (and (== local3 95) (< (ego x?) 203))
				(= securityDoor 1)
			)
		else
			(= local6 2)
			(self stopUpd:)
		)
	)
)

(instance tPanelLine of Prop
	(properties
		view 111
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: 1 setLoop: 4 posn: 0 0 setPri: 4)
	)
)

(instance bPanelLine of Prop
	(properties
		view 111
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: 1 setLoop: 4 setPri: 4)
	)
)

(instance scanner of Prop
	(properties
		view 111
		loop 5
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: 1 setPri: 6)
	)
)

(instance face of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			setPri: 14
			view: 105
			x: (if (== (ego loop?) 1) 219 else 202)
			y: (if (== (ego loop?) 1) 81 else 80)
			setLoop: (if (== (ego loop?) 1) 3 else 2)
		)
	)
)

(instance egoArea of Cage
	(properties
		top 117
		bottom 132
		right 320
	)
)

(instance hallSound of Sound
	(properties
		number 14
		loop -1
	)
)

(instance doorSound of Sound
	(properties
		number 49
		priority 1
	)
)

(instance ouchSound of Sound
	(properties
		number 57
		priority 1
	)
)

(instance scannerSound of Sound
	(properties
		number 67
		priority 1
	)
)

(instance keycardSound of Sound
	(properties
		number 74
		priority 1
	)
)

(instance searchSound of Sound
	(properties
		number 60
		priority 1
	)
)
