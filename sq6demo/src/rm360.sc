;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include game.sh) (include "360.shm")
(use Main)
(use SQRoom)
(use DText)
(use String)
(use Print)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm360 0
)

(local
	local0 =  9
	bjornPoly
	local2
	local3
	newDText
)
(instance rm360 of SQRoom
	(properties
		noun N_ROOM
		picture 360
	)
	
	(method (init)
		(if (Btst fInIntro)
			(sfxAmbiance play:)
			(sound1 number: 121 flags: 1 loop: 1 play:)
			(theIconBar disable:)
			(keyDownHandler addToFront: self)
			(mouseDownHandler addToFront: self)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init: 13 138 294 138 272 123 269 112
						239 112 225 98 161 91 107 96 77
						99 46 106 53 113 22 115 13 118
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 208 126 208 133 192 133 192 126
					yourself:
				)
				(cond 
					((Btst fBjornPluggedIn)
						(= bjornPoly
							((Polygon new:)
								type: PBarredAccess
								init: 89 120 94 105 160 96 221 103
									230 111 226 118 219 122 182 125
									167 134 61 134 61 125
								yourself:
							)
						)
					)
					((Btst fBjornEatChow)
						(= bjornPoly
							((Polygon new:)
								type: PBarredAccess
								init: 91 105 162 100 225 106 231
									112 227 118 208 123 190 124
									160 132 126 127 96 121 87 114
								yourself:
							)
						)
					)
					(else
						(= bjornPoly
							((Polygon new:)
								type: PBarredAccess
								init: 91 105 160 95 225 106
									230 111 227 118 208 123
									190 124 168 128 126 127
									96 121 87 114
								yourself:
							)
						)
					)
				)
		)
		(super init:)
		(if (and (!= prevRoomNum 460) (not (Btst fInIntro)))
			(sound1 number: 212 loop: -1 play:)
		)
		(theGame handsOff:)
		(if (Btst fInIntro)
			(viewScreen init: approachVerbs: V_DO)
		else
			(bjornLeader init: approachVerbs: V_DO 26)
			(bjornShip init: approachVerbs: V_DO)
		)
		(cond 
			((Btst fInIntro) (curRoom setScript: sIntroScript))
			(
				(and
					(== prevRoomNum 460)
					(or (== selectedRoom 0) (== selectedRoom 360))
				)
				(ego
					normalize:
					setScale: 0
					posn: 32 123
					setHeading: 315
					init:
				)
				(theGame handsOn:)
			)
			((== prevRoomNum 460) (curRoom setScript: sExitThruComPost))
			(else (curRoom setScript: sEnterRoom))
		)
		(captain init: approachVerbs: V_DO)
		(leftGuy init: approachVerbs: V_DO)
		(rightGuy init: approachVerbs: V_DO)
		(chair1 init: approachVerbs: V_DO)
		(chair2 init: approachVerbs: V_DO)
		(chair3 init: approachVerbs: V_DO)
		(chair4 init: approachVerbs: V_DO)
		(station1 init: approachVerbs: V_DO)
		(station2 init: approachVerbs: V_DO)
		(station3 init: approachVerbs: V_DO)
		(station4 init: approachVerbs: V_DO)
		(sandbox init: approachVerbs: V_DO)
		(chairControls init: approachVerbs: V_DO)
		(sandboxControls init: approachVerbs: V_DO)
		(captainControls init: approachVerbs: V_DO)
		(teleportTubeTop init: approachVerbs: V_DO)
		(teleportTubeBottom init: approachVerbs: V_DO)
		(comPost init: approachVerbs: V_DO)
		(outlet init: approachVerbs: V_DO V_CLAPFIXED)
		(captainChair init: approachVerbs: V_DO V_BJORNCHOW)
		(if (Btst fClapmasterPluggedIn) (clapmaster init: approachVerbs: V_DO))
	)
	
	(method (dispose)
		(ego normalize:)
		(if (Btst fInIntro)
			(theIconBar enable:)
			(keyDownHandler delete: self)
			(mouseDownHandler delete: self)
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(Btst fInIntro)
				(event type?)
				(== curRoomNum newRoomNum)
			)
			(event claimed: TRUE)
			(theGame setCursor: theDefaultCursor TRUE)
			(switch
				(Print
					fore: 84
					back: 86
					addTitle: 27 0 1 1 0
					addButton: 0 27 0 2 1 27 0 0
					addButton: 1 27 0 3 1 73 0 0
					addButton: 2 27 0 4 1 0 0 0
					init:
				)
				(0 0)
				(1 (= quit TRUE))
				(2
					(curRoom setScript: sSkipIntro)
				)
			)
			(theGame setCursor: waitCursor TRUE)
		else
			(super handleEvent: event)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (and (!= newRoomNumber 460) (not (Btst fInIntro)))
			(sound1 fade:)
		)
		(super newRoom: newRoomNumber)
	)
	
	(method (notify)
		(curRoom setScript: sUseBelt)
	)
)

(instance sUseBelt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: setCursor: waitCursor TRUE)
				(ego setHeading: 90 self)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(ego view: 27 loop: 0 cel: 0 setCycle: CycleTo 1 1 self)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(messager say: N_THE_END 0 0 1 self)
				(sound1 number: 105 flags: 1 loop: -1 setVol: 127 play:)
			)
			(3 (ego setCycle: EndLoop self))
			(4
				(messager say: 24 0 0 2 self)
			)
			(5
				(leftGuy
					view: 14
					loop: 2
					cel: 15
					cycleSpeed: local0
					setCycle: BegLoop self
				)
				(rightGuy
					view: 14
					loop: 0
					cel: 15
					cycleSpeed: local0
					setCycle: BegLoop self
				)
				(captain
					view: 14
					loop: 1
					cel: 9
					cycleSpeed: local0
					setCycle: BegLoop self
				)
				(sFX number: 938 loop: 1 play:)
			)
			(6
				(captain view: 7 loop: 3 cel: 2 setPri: 115)
			)
			(7)
			(8
				(messager say: N_THE_END 0 0 3 self)
			)
			(9 (proc0_7 1 2 self))
			(10
				(cast eachElementDo: #dispose)
				(curRoom drawPic: -1)
				(= cycles 1)
			)
			(11
				(messager sayRange: N_THE_END 0 0 4 5 self)
			)
			(12
				(ego
					put: iBjornBelt
					put: iBjornChow
					put: iBrokenClapmaster
					put: iFixedClapmaster
					put: iDuctTape
					put: iPin
					put: iPliers
					put: iWrappedPliers
				)
				(inventory
					highlightedIcon: 0
					selectedInvIcon: 0
					numInv: 0
				)
				(theIconBar disable:)
				(mouseDownHandler addToFront: self)
				(= local3 (String format: {Computer... end simulation.}))
				((= newDText (DText new:))
					text: (KString StrDup (local3 data?))
					font: userFont
					fore: 84
					posn: 105 65
					setSize: 240
					setPri: 255
					init:
				)
				(= ticks 480)
			)
			(13
				(if (mouseDownHandler contains: self)
					(mouseDownHandler delete: self)
				)
				(newDText dispose:)
				(local3 dispose:)
				(= local2 100)
				(while (> local2 0)
					(Palette PalIntensity 80 86 local2)
					(FrameOut)
					(= local2 (- local2 3))
				)
				(Palette PalIntensity 80 86 0)
				(= cycles 1)
			)
			(14
				(sound1 fade: 0 25 10 1 self)
			)
			(15
				(Bclr fBjornEatChow)
				(Bclr fClapmasterPluggedIn)
				(Bclr fGotClapmaster)
				(Bclr fGotPin)
				(Bclr fBjornPluggedIn)
				(Bclr fGotChow)
				(Bclr fChowReady)
				(Bclr fWalkActive)
				(Bclr fLookActive)
				(Bclr fDoActive)
				(Bclr fTalkActive)
				(Bclr fHelpActive)
				(Bclr fControlActive)
				(Bclr fControlActive) ;EO: Yes, it used the same number
				(Bclr fExitActive)
				(Bclr fInvActive)
				(Bclr fFixingClapmaster)
				(Bclr fVisitedReplicator)
				(= selectedRoom 0)
				(= gameFlags 0)
				(= endGameFlags 0)
				(= oldCurIcon 0)
				(= gGSQIconbarCurIcon 0)
				(curRoom newRoom: 100)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (!= (event type?) mouseUp)
			(mouseDownHandler delete: self)
			(= ticks 0)
			(self changeState: (+ state 1))
		)
	)
)

(instance sSkipIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(sound2 fade:)
				(proc0_7 1 4 self)
			)
			(2
				(curRoom newRoom: 450)
				(self dispose:)
			)
		)
	)
)

(instance sEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(sound2 number: 941 loop: 1 play:)
				(ego
					view: 364
					loop: 0
					cel: 0
					posn: 32 123
					setPri: 115
					setSpeed: local0
					setCycle: EndLoop self
					init:
				)
			)
			(2
				(ego normalize: setLoop: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitThruComPost of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego normalize: loop: 3 setSpeed: local0 init:)
				(= ticks 40)
			)
			(1
				(ego setHeading: 180)
				(= ticks 90)
			)
			(2
				(sound2 number: 926 loop: 1 play:)
				(ego
					view: 3630
					loop: 0
					cel: 0
					posn: 32 123
					setPri: 115
					setCycle: EndLoop self
				)
			)
			(3
				(curRoom newRoom: selectedRoom)
				(self dispose:)
			)
		)
	)
)

(instance sPlugInClapmaster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (!= (ego x?) 131) (!= (ego y?) 127))
					(ego setMotion: PolyPath 131 127 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 20
					loop: 0
					cel: 0
					setPri: 117
					setSpeed: local0
					setCycle: EndLoop self
				)
			)
			(2
				(clapmaster init: approachVerbs: V_DO)
				(ego put: iFixedClapmaster)
				(Bset fClapmasterPluggedIn)
				(ego normalize: setHeading: 0)
				(= cycles 3)
			)
			(3
				(if (Btst fBjornEatChow)
					(curRoom setScript: sPlugInBjorn)
					(self dispose:)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance sDropBjornChow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load RES_VIEW 21)
				(ego
					view: 20
					loop: 1
					cel: 0
					setSpeed: local0
					setCycle: EndLoop self
				)
			)
			(1
				(ego view: 21 loop: 1 cel: 0 setCycle: Forward put: 1)
				(bjornChow init: approachVerbs: V_DO)
				(sFX number: 119 loop: 1 play:)
				(bjornLeader
					view: 15
					setCycle: Walk
					setMotion: MoveTo 239 124 self
				)
			)
			(2
				(bjornLeader setMotion: MoveTo 158 128 self)
			)
			(3
				(bjornLeader
					view: 22
					loop: 0
					cel: 0
					setPri: 118
					setSpeed: local0
					setCycle: CycleTo 1 1 self
				)
			)
			(4
				(sFX fade: 0 10 10 1)
				(bjornChow dispose:)
				(bjornLeader setCycle: EndLoop self)
			)
			(5
				(ego normalize:)
				(Load RES_WAVE 321)
				(sound2 number: 321 loop: -1 play:)
				(bjornLeader
					loop: 1
					cel: 0
					approachX: 130
					approachY: 128
					approachVerbs: V_DO
					case: 2
					setCycle: Forward
				)
				(Bset fBjornEatChow)
				((curRoom obstacles?) delete: bjornPoly)
				(bjornPoly dispose:)
				(curRoom
					addObstacle:
						(= bjornPoly
							((Polygon new:)
								type: PBarredAccess
								init: 91 105 162 100 225 106 231 112
									227 118 208 123 190 124 160 132
									126 127 96 121 87 114
								yourself:
							)
						)
				)
				(if (Btst fClapmasterPluggedIn)
					(= ticks 180)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(6
				(curRoom setScript: sPlugInBjorn)
				(self dispose:)
			)
		)
	)
)

(instance sPlugInBjorn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 23
					loop: 2
					cel: 0
					setSpeed: local0
					setCycle: EndLoop self
				)
				(clapmasterCord dispose:)
			)
			(1
				(ego
					view: 23
					loop: 0
					cel: 0
					setCycle: CycleTo (- (ego lastCel:) 2) 1 self
				)
			)
			(2
				(bjornLeader loop: 1 cel: 0 setCycle: 0)
				(= cycles 5)
			)
			(3 (ego setCycle: EndLoop self))
			(4
				(bjornLeader loop: 2 cel: 0 setCycle: Forward)
				(ego
					normalize:
					loop: 2
					setCycle: Walk
					setSpeed: egoSpeed
					setMotion: MoveTo 37 128 self
				)
			)
			(5
				(ego setHeading: 90)
				(= cycles 20)
			)
			(6
				(Load RES_WAVE 937)
				(sound2 stop:)
				(ego
					view: 23
					loop: 1
					cel: 0
					setSpeed: local0
					setCycle: EndLoop self
				)
			)
			(7
				(sFX number: 937 loop: 1 setVol: 127 play: self)
			)
			(8
				(ego cel: 2 setCycle: EndLoop self)
			)
			(9
				(sFX number: 937 loop: 1 play: self)
			)
			(10
				(ego normalize:)
				(sFX number: 922 loop: 1 flags: 1 play:)
				(bjornLeader
					view: 24
					loop: 0
					cel: 0
					x: 159
					y: 125
					setCycle: EndLoop self
				)
			)
			(11
				(bjornLeader
					view: 24
					loop: 1
					cel: 0
					setCycle: CycleTo 10 1 self
				)
			)
			(12
				(sFX number: 910 loop: 1 play:)
				(bjornLeader setCycle: EndLoop self)
			)
			(13
				(toast init: approachVerbs: V_DO)
				(bjornLeader
					view: 25
					loop: 3
					cel: 0
					approachX: 106
					approachY: 137
					case: 3
				)
				(= cycles 1)
			)
			(14
				(Bset fBjornPluggedIn)
				((curRoom obstacles?) delete: bjornPoly)
				(bjornPoly dispose:)
				(curRoom
					addObstacle:
						(= bjornPoly
							((Polygon new:)
								type: PBarredAccess
								init: 89 120 94 105 160 96 221 103
									230 111 226 118 219 122 182 125
									167 134 61 134 61 125
								yourself:
							)
						)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetBelt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 26
					loop: 0
					cel: 0
					setSpeed: local0
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(messager say: N_BJORN_LEADER V_DO C_GOT_BELT 0 self)
			)
			(2
				(bjornLeader view: 25 loop: 3 cel: 1)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego normalize: loop: 3 get: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sIntroScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 10
					loop: 0
					cel: 0
					x: 163
					y: 29
					cycleSpeed: 14
					init:
				)
				(= cycles 1)
			)
			(1 (ego setCycle: EndLoop self))
			(2
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(sound1 fade:)
				(Load RES_VIEW 11)
				(sFX play:)
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4
				(sFX play:)
				(ego setCel: 0 setCycle: EndLoop self)
			)
			(5
				(sFX play:)
				(ego setCel: 0 setCycle: EndLoop self)
			)
			(6
				(ego view: 11 loop: 0 cel: 0 setCycle: EndLoop self)
				(Load RES_VIEW 13)
				(bjornShip init: setScript: sBjornShipApproaches)
			)
			(7
				(ego setScript: sDoSqueegee)
				(bottle
					init:
					setPri: (- (ego priority?) 1)
					setCycle: EndLoop self
				)
			)
			(8
				(bottle loop: 3 cel: 0 setCycle: Forward)
				(self dispose:)
			)
		)
	)
)

(instance sDoSqueegee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sFX number: 902 loop: 1 play:)
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(sFX number: 904 loop: 1 play:)
				(ego setCel: 0 setCycle: EndLoop self)
			)
			(3 (ego setCycle: BegLoop self))
			(4 (self changeState: 0))
			(5 (self dispose:))
		)
	)
)

(instance sBjornShipApproaches of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bjornShip
					loop: 0
					cel: 0
					cycleSpeed: local0
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 90))
			(2
				(bjornShip loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3 (= ticks 90))
			(4
				(sound1 fade:)
				(sound2 number: 125 flags: 1 loop: 1 play:)
				(bjornShip loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5 (= ticks 90))
			(6
				(bjornShip
					view: 13
					loop: 0
					cel: 0
					cycleSpeed: 14
					setCycle: EndLoop self
				)
				(Load RES_VIEW 18)
			)
			(7
				(bjornShip loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(8
				(bjornShip view: 8 loop: 0 setPri: 12)
				(= ticks 120)
			)
			(9
				(= next sBjornEnter)
				(self dispose:)
			)
		)
	)
)

(instance sBjornEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sDoSqueegee dispose:)
				(sound2 number: 126 flags: 1 loop: 1 play:)
				(sFX number: 923 loop: 1 play:)
				(bjornLeader
					init:
					posn: 232 135
					approachVerbs: V_DO
					setCycle: EndLoop self
				)
				(bjornMember init: setCycle: EndLoop)
			)
			(1
				(tail dispose:)
				(mouse dispose:)
				(captain loop: 3 cel: 0 setScript: 0 setCycle: EndLoop)
				(ego
					loop: 4
					x: 164
					y: 29
					cel: 0
					cycleSpeed: 7
					setCycle: CycleTo 1 1 self
				)
			)
			(2 (= ticks 60))
			(3
				(sFX number: 905 loop: 1 play:)
				(ego setCycle: CycleTo 9 1 self)
			)
			(4
				(sFX number: 906 loop: 1 play:)
				(bottle dispose:)
				(ego setCycle: EndLoop self)
			)
			(5 (ego hide:) (= ticks 90))
			(6
				(ego setLoop: 5 setCel: 0 x: 201 y: 34 show:)
				(= cycles 1)
			)
			(7
				(sound2 number: 127 flags: 1 loop: 1 play:)
				(sfxAmbiance stop:)
				(bjornLeader
					view: 17
					loop: 1
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(8
				(bjornLeader setCycle: EndLoop self)
			)
			(9
				(sFX number: 908 loop: 1 play:)
				(leftGuy view: 14 loop: 2 cel: 0 setCycle: EndLoop self)
				(rightGuy view: 14 loop: 0 cel: 0 setCycle: EndLoop self)
				(captain view: 14 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(10)
			(11)
			(12
				(leftGuy view: 8 loop: 3 setPri: 83)
				(rightGuy view: 8 loop: 1 setPri: 87)
				(= next sBjornControl)
				(self dispose:)
			)
		)
	)
)

(instance sBjornControl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bjornMember
					view: 16
					signal: (| (ego signal?) fixedLoop)
					loop: 2
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 37 118 self
				)
			)
			(1
				(sFX number: 926 loop: 1 play:)
				(bjornMember loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2
				(bjornMember
					loop: 1
					cel: 0
					posn: 34 122
					cycleSpeed: 10
					setPri: 115
				)
				(= ticks 10)
			)
			(3
				(sFX number: 910 loop: 1 play:)
				(bjornMember setCycle: EndLoop self)
			)
			(4
				(bjornMember dispose:)
				(bjornLeader
					view: 15
					cel: 2
					setCycle: Walk
					setMotion: MoveTo 248 104 self
				)
			)
			(5
				(bjornLeader view: 19 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(6 (= ticks 90))
			(7
				(sFX number: 909 play:)
				(bjornLeader loop: 1 cel: 0 setCycle: RandCycle)
				(= ticks 180)
			)
			(8
				(sFX fade:)
				(proc0_7 1 4 self)
			)
			(9
				(messager say: N_INTRO 0 0 0 self)
			)
			(10
				(curRoom newRoom: 450)
				(self dispose:)
			)
		)
	)
)

(instance bjornLeader of Actor
	(properties
		noun N_BJORN_LEADER
		sightAngle 40
		x 248
		y 104
		signal (| ignrAct skipCheck setBaseRect canUpdate) ;$5021
	)
	
	(method (init)
		(cond 
			((Btst fInIntro)
				(self view: 18 loop: 0)
			)
			((Btst fBjornPluggedIn)
				(self
					view: 25
					loop: 3
					cel: (if (ego has: iBjornBelt) 1 else 0)
					x: 159
					y: 125
					approachX: 106
					approachY: 137
					approachVerbs: V_DO
				)
				(toast init: approachVerbs: V_DO)
			)
			((Btst fBjornEatChow)
				(self
					view: 22
					loop: 1
					cel: 0
					x: 158
					y: 128
					approachX: 130
					approachY: 128
					approachVerbs: V_DO
					setPri: 118
					setCycle: Forward
				)
				(Load RES_WAVE 321)
				(sFX number: 321 loop: -1 play: setVol: 127)
			)
			(else
				(self
					view: 19
					loop: 2
					cel: 0
					approachX: 231
					approachY: 105
					setCycle: RandCycle
				)
				(Load RES_WAVE 909)
				(sFX number: 909 loop: -1 play:)
			)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					(
						(and
							(== (self view?) 25)
							(== (self loop?) 3)
							(not (ego has: iBjornBelt))
						)
						(curRoom setScript: sGetBelt)
					)
					(
						(and
							(== (self view?) 22)
							(== (self loop?) 1)
							(Btst fClapmasterPluggedIn)
						)
						(curRoom setScript: sPlugInBjorn)
					)
					((and (== (self view?) 2) (== (self loop?) 1))
						(messager say: noun theVerb C_BJORN_EATING)
					)
					((and (== (self view?) 25) (== (self loop?) 3))
						(messager say: noun theVerb C_BJORN_OFF)
					)
					(else
						(super doVerb: theVerb &rest)
					)
				)
			)
			(21
				(if (Btst fBjornEatChow)
					(curRoom setScript: sPlugInClapmaster)
				else
					(super doVerb: theVerb)
				)
			)
			(2
				(cond 
					(
					(and (== (self view?) 22) (== (self loop?) 1)) (messager say: noun theVerb C_BJORN_EATING))
					(
					(and (== (self view?) 25) (== (self loop?) 3)) (messager say: noun theVerb C_BJORN_OFF))
					(else (messager say: noun theVerb))
				)
			)
			(1
				(cond 
					(
					(and (== (self view?) 22) (== (self loop?) 1)) (messager say: noun theVerb C_BJORN_EATING))
					(
					(and (== (self view?) 25) (== (self loop?) 3)) (messager say: noun theVerb C_BJORN_OFF))
					(else (messager say: noun theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bjornMember of Actor
	(properties
		sightAngle 40
		x 69
		y 132
		view 18
		loop 1
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
)

(instance leftGuy of Prop
	(properties
		noun 19
		sightAngle 40
		approachX 89
		approachY 100
		x 93
		y 92
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(if (Btst fInIntro)
			(self view: 365 loop: 2 setPri: 83 setCycle: RandCycle)
		else
			(self view: 8 loop: 3 setPri: 83)
		)
		(super init:)
	)
)

(instance rightGuy of Prop
	(properties
		noun N_RIGHT_GUY
		sightAngle 40
		approachX 269
		approachY 112
		x 265
		y 104
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(if (Btst fInIntro)
			(self view: 365 loop: 0 setPri: 87 setCycle: RandCycle)
		else
			(self view: 8 loop: 1 setPri: 87)
		)
		(super init:)
	)
)

(instance bjornShip of Prop
	(properties
		noun N_VIEWSCREEN
		sightAngle 40
		approachX 161
		approachY 91
		x 119
		y 8
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(if (Btst fInIntro)
			(self view: 12 loop: 0 setPri: 12)
		else
			(self view: 8 loop: 0 setPri: 12)
		)
		(super init:)
	)
)

(instance bottle of Prop
	(properties
		sightAngle 40
		x 163
		y 29
		view 11
		loop 2
		signal (| ignrAct skipCheck setBaseRect canUpdate)
		cycleSpeed 10
	)
)

(instance mouse of Prop
	(properties
		x 242
		y 132
		view 7
		loop 2
		cel 1
	)
	
	(method (init)
		(self cycleSpeed: local0 setCycle: Forward setPri: 115)
		(super init:)
	)
)

(instance tail of Prop
	(properties
		x 186
		y 110
		view 7
		loop 1
		cel 1
	)
	
	(method (init)
		(self cycleSpeed: local0 setCycle: Forward setPri: 116)
		(super init:)
	)
)

(instance captain of Prop
	(properties
		noun 26
		sightAngle 40
		approachX 199
		approachY 137
		x 199
		y 130
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(if (Btst fInIntro)
			(self
				view: 7
				cel: 0
				loop: 0
				cycleSpeed: 5
				setScript: sCaptainCycle
				setPri: 115
			)
			(tail init:)
			(mouse init:)
		else
			(self view: 8 setPri: 115 loop: 2 cel: 15)
		)
		(super init:)
	)
)

(instance sCaptainCycle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (RandTime 120 500))
			)
			(1 (captain setCycle: EndLoop self))
			(2 (captain setCycle: BegLoop self))
			(3 (self changeState: 0))
		)
	)
)

(instance viewScreen of View
	(properties
		noun N_VIEWSCREEN
		sightAngle 40
		x 119
		y 8
		view 9
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(self setPri: 10)
		(super init:)
	)
)

(instance outlet of View
	(properties
		noun N_OUTLET
		sightAngle 40
		approachX 130
		approachY 128
		x 146
		y 102
		view 8
		loop 4
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(self setPri: 114)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(21
				(curRoom setScript: sPlugInClapmaster)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance clapmaster of View
	(properties
		noun N_CLAPMASTER
		sightAngle 40
		approachX 130
		approachY 128
		x 154
		y 105
		view 25
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(self setPri: 115)
		(if (not (Btst fBjornPluggedIn))
			(clapmasterCord init: approachVerbs: V_DO)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(!= (bjornLeader view?) 25)
				(!= (bjornLeader loop?) 3)
				(Message MsgSize 360 noun theVerb 0 C_GOT_BELT)
			)
			(messager say: noun theVerb)
		else
			(super doVerb: &rest)
		)
	)
)

(instance bjornChow of View
	(properties
		noun N_BJORN_CHOW
		sightAngle 40
		x 156
		y 78
		priority 115
		view 25
		loop 1
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(self setPri: 115)
		(super init:)
	)
)

(instance clapmasterCord of View
	(properties
		noun N_CLAPMASTER
		sightAngle 40
		approachX 130
		approachY 128
		x 154
		y 106
		view 25
		cel 1
		signal (| ignrAct skipCheck setBaseRect canUpdate)
	)
	
	(method (init)
		(self setPri: 115)
		(super init:)
	)
)

(instance toast of View
	(properties
		noun N_TOAST
		approachX 74
		approachY 135
		x 74
		y 131
		view 25
		loop 2
	)
)

(instance comPost of Feature
	(properties
		noun N_COMPOST
		nsLeft 15
		nsTop 74
		nsRight 27
		nsBottom 82
		sightAngle 40
		approachX 32
		approachY 123
		x 20
		y 103
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (curRoom newRoom: 460))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chairControls of Feature
	(properties
		noun N_CHAIR_CONTROLS
		nsLeft 166
		nsTop 104
		nsRight 178
		nsBottom 112
		sightAngle 40
		approachX 183
		approachY 127
		x 172
		y 113
	)
)

(instance captainChair of Feature
	(properties
		noun N_CAPTAIN_CHAIR
		sightAngle 40
		approachX 131
		approachY 127
		x 165
		y 80
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 97 111 99 116 110 120 158 123 194
						122 218 118 223 115 223 109 220 105
						219 67 199 62 176 59 159 58 125 61
						102 67 102 105
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BJORNCHOW
				(curRoom setScript: sDropBjornChow)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance captainControls of Feature
	(properties
		noun N_CAPTAIN_CONTROLS
		sightAngle 40
		approachX 131
		approachY 127
		x 165
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 217 66 105 66 99 61 112 57
						112 51 116 46 135 45 138 53
						152 52 172 51 184 52 185 49
						197 50 209 52 207 55 222 59
						223 62
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sandbox of Feature
	(properties
		noun N_SANDBOX
		sightAngle 40
		approachX 289
		approachY 135
		x 304
		y 127
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 319 112 288 117 289 129 309 138 319 138
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sandboxControls of Feature
	(properties
		noun N_SANDBOX_CONTROLS
		nsLeft 310
		nsTop 130
		nsRight 317
		nsBottom 136
		sightAngle 40
		approachX 289
		approachY 135
		x 313
		y 133
	)
)

(instance teleportTubeTop of Feature
	(properties
		noun N_TELEPORT_TUBE_TOP
		approachX 31
		approachY 122
		x 165
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 13 0 13 25 31 27 49 25 49 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance teleportTubeBottom of Feature
	(properties
		noun N_TELEPORT_TUBE_BOTTOM
		sightAngle 40
		approachX 31
		approachY 122
		x 165
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 12 122 19 118 31 117 43 118 50 121 43 125 31 126 18 125
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance chair1 of Feature
	(properties
		noun N_CHAIR
		sightAngle 40
		approachX 63
		approachY 104
		x 63
		y 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 61 100 57 98 52 97 51 94 65
						92 65 89 59 89 62 82 68 81 72
						82 74 85 74 89 71 89 70 93 73
						94 73 96 67 98 67 99
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance chair2 of Feature
	(properties
		noun N_CHAIR
		sightAngle 40
		approachX 89
		approachY 100
		x 89
		y 96
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 92 96 88 93 83 92 83 89 96
						88 96 85 91 84 93 77 100 76
						102 75 102 91 98 93 98 95
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance chair3 of Feature
	(properties
		noun N_CHAIR
		sightAngle 40
		approachX 227
		approachY 100
		x 226
		y 95
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 229 96 234 93 239 92 238 89 227
						88 226 85 232 84 228 77 220 76 220
						91 224 93 223 95
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance chair4 of Feature
	(properties
		noun N_CHAIR
		sightAngle 40
		approachX 269
		approachY 112
		x 257
		y 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 260 100 262 97 269 95 269 93
						258 92 257 90 263 89 259 82 254
						82 249 82 248 88 251 89 252 93
						249 94 253 97 254 99
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance station1 of Feature
	(properties
		noun N_STATION1
		sightAngle 40
		approachX 63
		approachY 104
		x 52
		y 83
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 73 84 68 78 68 66 39 69 39 82 43 88
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance station2 of Feature
	(properties
		noun N_STATION2
		sightAngle 40
		approachX 89
		approachY 100
		x 85
		y 78
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 77 84 102 80 102 62 72 65 72 78
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance station3 of Feature
	(properties
		noun N_STATION3
		sightAngle 40
		approachX 227
		approachY 100
		x 231
		y 80
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 243 83 249 77 249 65 218 63 218 80
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance station4 of Feature
	(properties
		noun N_STATION4
		sightAngle 40
		approachX 269
		approachY 112
		x 264
		y 83
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: PTotalAccess
					init: 277 88 283 82 283 69 253 66 253 76 246 85
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sfxAmbiance of SQSound
	(properties
		flags mNOPAUSE
		number 901
		loop FOREVER
	)
)

(instance sFX of SQSound
	(properties
		flags mNOPAUSE
		number 903
	)
)
