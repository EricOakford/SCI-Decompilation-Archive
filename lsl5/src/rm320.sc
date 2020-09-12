;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use PrintD)
(use RandCyc)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	cloudState
	local1
	local2
	printRet
)
(procedure (localproc_10e6)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(HandsOn)
	(User canControl: FALSE)
	(theIconBar disable: ICON_WALK)
)

(instance rm320 of LLRoom
	(properties
		picture 320
		style IRISOUT
	)
	
	(method (init)
		(if (== prevRoomNum 101)
			(switch
				(= printRet
					(PrintD {Enter dream to debug:}
						#new
						#button {Dream of Athens} 1
						#new
						#button {Wake up from Athens} 5
						#new
						#button {Dream of Venice} 2
						#new
						#button {Wake up from Venice} 6
						#new
						#button {Dream of Taj Mahal} 3
						#new
						#button {Wake up from Taj Mahal} 7
						#new
						#button {Dream of Casablanca} 4
					)
				)
				(1 (= larryDreamNum 0))
				(2 (= larryDreamNum 1))
				(3 (= larryDreamNum 2))
				(4 (= larryDreamNum 3))
				(5
					(= prevRoomNum 200)
					(= larryDreamNum 0)
				)
				(6
					(= prevRoomNum 200)
					(= larryDreamNum 1)
				)
				(7
					(= prevRoomNum 200)
					(= larryDreamNum 2)
				)
			)
		)
		(super init:)
		(if (!= prevRoomNum 200)
			(theMusic number: 321 loop: -1 play:)
		)
		(cloud
			init:
			setStep: 1 1
			posn: 244 (Random 30 44)
			setMotion: MoveTo 63 (cloud y?) cloud
		)
		(switch prevRoomNum
			(200
				(Load SOUND 312)
				(= local1 1)
				(switch larryDreamNum
					(0
						(larry init: setCel: 1 stopUpd:)
						(if (== global157 1)
							(= larryDreamNum 1)
						else
							(= larryDreamNum 2)
						)
						(Load SOUND 347)
						(self setScript: sWakeUpAthens)
					)
					(1
						(larry init: setCel: 1 stopUpd:)
						(if (and (Btst fAfterHammer) (Btst fAfterBiaz))
							(= larryDreamNum 3)
						else
							(= larryDreamNum 2)
						)
						(self setScript: sWakeUpVenice)
					)
					(2
						(if (and (Btst fAfterHammer) (Btst fAfterBiaz))
							(= larryDreamNum 3)
						else
							(= larryDreamNum 1)
						)
						(self setScript: sWakeUpTaj)
					)
				)
			)
			(else 
				(Load VIEW 322)
				(larry init: stopUpd:)
				(body init: stopUpd:)
				(arm init: stopUpd:)
				(tray init: stopUpd:)
				(arm2 init: stopUpd:)
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(theMagazine init:)
					(magazine init:)
				)
				(self setScript: sFlyingCoach)
			)
		)
		(HandsOff)
	)
	
	(method (doit)
		(super doit:)
		(if local1
			(Palette PALCycle local2 (+ local2 7) 6)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 320 0)
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(TimePrint 320 1)
				)
			)
			(verbTalk
				(TimePrint 320 2)
			)
			(verbDo
				(TimePrint 320 3)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance sFlyingCoach of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(User canInput: 1)
				(Load SOUND 320)
				(switch larryDreamNum
					(0
						(SetFFRoom 400)
						(Load VIEW 330)
						(Load VIEW 335)
						(Load VIEW 341)
						(Load PICTURE 330)
						(Load PICTURE 335)
						(Load PICTURE 340)
						(Load SOUND 330)
					)
					(1
						(SetFFRoom 600)
						(Load VIEW 350)
						(Load VIEW 351)
						(Load VIEW 321)
						(Load VIEW 356)
						(Load PICTURE 350)
						(Load PICTURE 355)
					)
					(2
						(SetFFRoom 800)
						(Load VIEW 370)
						(Load VIEW 371)
						(Load PICTURE 370)
						(Load SOUND 370)
					)
					(3
						(SetFFRoom 385)
						(Load VIEW 380)
						(Load PICTURE 380)
					)
				)
				(localproc_10e6)
				(= seconds (Random 10 20))
			)
			(2
				(HandsOff)
				(larry setCel: 1)
				(theMusic number: 321 fade: 0 15 10 1)
				(theMusic2 number: 320 loop: -1 play: 10 fade: 127 15 5 0)
				(= seconds 3)
			)
			(3
				(larry addToPic: dispose:)
				(= seconds 2)
			)
			(4
				(dreamProp1 init: view: 322 setCel: 1 posn: 146 37)
				(= ticks 40)
			)
			(5
				(dreamProp1 setCel: 2 posn: 171 51)
				(= ticks 40)
			)
			(6
				(dreamProp1 setCel: 3 posn: 195 78)
				(theMusic2 fade: 0 15 5 1)
				(= ticks 60)
			)
			(7
				(switch larryDreamNum
					(0
						(theMusic number: 330 loop: 1 play: 30 fade: 127 15 10 0)
					)
					(1
						(theMusic number: 350 loop: -1 play: 30 fade: 127 15 10 0)
					)
					(2
						(theMusic2
							number: 370
							loop: -1
							play: 30
							fade: 127 15 10 0
						)
					)
					(3
						(theMusic number: 381 loop: -1 play: 30 fade: 127 15 10 0)
					)
				)
				(= ticks 40)
			)
			(8
				(dreamProp1 setCel: 4 posn: 201 122)
				(= ticks 30)
			)
			(9
				(= local1 1)
				(switch larryDreamNum
					(0
						(self setScript: sDreamAthens)
					)
					(1
						(self setScript: sDreamVenice)
					)
					(2 (self setScript: sDreamTaj))
					(3 (self setScript: sDreamCasa))
				)
			)
		)
	)
)

(instance larry of Actor
	(properties
		x 134
		y 70
		description {your head}
		view 321
		loop 1
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 320 4)
				(TimePrint 320 5 #at -1 185)
			)
			(verbTalk
				(TimePrint 320 6)
			)
			(verbDo
				(TimePrint 320 7)
				(TimePrint 320 8 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance tray of Prop
	(properties
		x 202
		y 204
		z 100
		description {the tray}
		view 325
		loop 4
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 320 9)
			)
			(verbTalk
				(TimePrint 320 10)
			)
			(verbDo
				(TimePrint 320 11)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(InRect 164 80 199 96 theObjOrX)
	)
)

(instance arm of Prop
	(properties
		x 126
		y 79
		description {your arm}
		lookStr {You've always liked this arm.}
		view 321
		loop 2
		priority 5
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance arm2 of Prop
	(properties
		x 173
		y 84
		description {your arm}
		lookStr {You've always liked this arm.}
		view 321
		loop 2
		cel 1
		priority 5
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance body of Prop
	(properties
		x 165
		y 125
		description {your body}
		view 321
		priority 2
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 320 12)
				(TimePrint 320 13 #at -1 185)
			)
			(verbTalk
				(larry doVerb: theVerb theItem)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cloud of Actor
	(properties
		x 244
		y 44
		description {the cloud}
		view 320
		loop 1
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 320 14)
			)
			(verbDo
				(TimePrint 320 15)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(switch (++ cloudState)
			(1
				(StartTimer (Random 2 5) 0 self)
			)
			(2
				(= cloudState 0)
				(cloud
					posn: 244 (Random 30 44)
					setMotion: MoveTo 63 (cloud y?) self
				)
			)
		)
	)
)

(instance patti of Prop
	(properties
		view 320
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance dreamProp1 of Prop
	(properties
		view 320
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance dreamProp2 of Prop
	(properties
		view 320
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance dreamProp3 of Prop
	(properties
		view 320
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance dreamProp4 of Prop
	(properties
		view 320
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance dreamProp5 of Prop
	(properties
		view 320
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance conf of Prop
	(properties
		x 181
		y 74
		view 340
		priority 14
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (cue)
		(super cue:)
		(switch (Random 1 10)
			(1
				(self
					show:
					setLoop: 0
					posn: 181 74
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(self
					show:
					setLoop: 0
					posn: 219 77
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(self
					show:
					setLoop: 0
					posn: 246 73
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(self
					show:
					setLoop: 1
					posn: 169 74
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(self
					show:
					setLoop: 1
					posn: 212 78
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(self
					show:
					setLoop: 1
					posn: 227 65
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(else 
				(self hide:)
				(StartTimer (Random 2 6) 1 self)
			)
		)
	)
)

(instance sSqeezeNuts of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic3 number: 322 loop: 1 play: self)
				(arm setCycle: EndLoop)
				(arm2 setCycle: EndLoop)
			)
			(1
				(arm setCel: 0)
				(arm2 setCel: 0)
				(= ticks 30)
			)
			(2 (self init:))
		)
	)
)

(instance magazine of View
	(properties
		x 208
		y 114
		description {the airline magazine}
		view 320
		priority 1
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 320 16)
			)
			(verbDo
				(ego get: iMagazine)
				(theMagazine dispose:)
				(SolvePuzzle 8)
				(TimePrint 320 17)
				(magazine dispose:)
				(self dispose:)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance MyForward of Forward
	
	(method (cycleDone)
		(client cycleSpeed: (Random 6 10))
		(super cycleDone: &rest)
	)
)

(instance sDreamAthens of Script
	
	(method (doit)
		(super doit:)
		(if (not state)
			(switch (theMusic prevSignal?)
				(10
					(theMusic prevSignal: 0)
					(patti setCycle: 0)
				)
				(-1
					(theMusic prevSignal: 0)
					(self cue:)
				)
			)
		)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine dispose:)
				)
				(dreamProp1 dispose:)
				(body dispose:)
				(arm dispose:)
				(tray dispose:)
				(cloud dispose:)
				(arm2 dispose:)
				(patti
					init:
					view: 330
					posn: 172 125
					cycleSpeed: 10
					setCycle: RandCycle
				)
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 40)
				(curRoom overlay: 330)
			)
			(1
				(= local1 0)
				(curRoom drawPic: (curRoom picture?))
				(Graph GFillRect 0 0 192 320 2 0 0)
				(larry init: addToPic: dispose:)
				(= local2 40)
				(curRoom overlay: 335)
				(patti view: 335 posn: 213 88 setCycle: 0)
				(theMusic2 number: 331 loop: -1 play:)
				(= ticks 10)
			)
			(2 (= local1 1) (= seconds 8))
			(3
				(= local1 0)
				(curRoom drawPic: (curRoom picture?))
				(Graph GFillRect 0 0 192 320 2 0 0)
				(larry init: addToPic: dispose:)
				(= local2 56)
				(curRoom overlay: 340)
				(patti
					view: 341
					setLoop: 0
					posn: 197 89
					setCel: 0
					setCycle: EndLoop
				)
				(conf init: setCycle: EndLoop conf)
				(= ticks 10)
			)
			(4
				(= local1 1)
				(dreamProp2
					init:
					view: 341
					setLoop: 3
					setCel: 0
					posn: 252 67
				)
				(= ticks 2)
			)
			(5
				(dreamProp2 setCel: 1 posn: 252 67)
				(= ticks 2)
			)
			(6
				(dreamProp2 setCel: 2 posn: 224 87)
				(= ticks 2)
			)
			(7
				(dreamProp2 setCel: 3 posn: 213 102 addToPic: dispose:)
				(= seconds 3)
			)
			(8
				(patti setCycle: BegLoop)
				(dreamProp3
					init:
					view: 341
					setLoop: 4
					setCel: 3
					posn: 163 63
				)
				(= ticks 2)
			)
			(9
				(dreamProp3 setCel: 4 posn: 169 70)
				(= ticks 2)
			)
			(10
				(dreamProp3 setCel: 5 posn: 177 78)
				(= ticks 2)
			)
			(11
				(dreamProp3 setCel: 6 posn: 184 88 addToPic: dispose:)
				(= seconds 3)
			)
			(12
				(patti setLoop: 1 setCel: 0 setCycle: EndLoop)
				(dreamProp4
					init:
					view: 341
					setLoop: 5
					setCel: 3
					posn: 205 69
				)
				(= ticks 2)
			)
			(13
				(dreamProp4 setCel: 4 posn: 215 78)
				(= ticks 2)
			)
			(14
				(dreamProp4 setCel: 5 posn: 230 91)
				(= ticks 2)
			)
			(15
				(dreamProp4 setCel: 6 posn: 236 103)
				(= seconds 2)
			)
			(16
				(patti setCycle: BegLoop)
				(conf dispose:)
				(= local1 0)
				(theMusic fade: 0 15 10 1)
				(theMusic2 fade: 0 15 10 1)
				(= seconds 2)
			)
			(17 (curRoom newRoom: 400))
		)
	)
)

(instance sWakeUpAthens of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SetFFRoom 310)
				(cloud hide:)
				(curRoom drawPic: (curRoom picture?))
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(curRoom overlay: 345)
				(theMusic2 number: 345 loop: 1 play:)
				(dreamProp2
					init:
					view: 345
					posn: 168 118
					setPri: 13
					setLoop: 0
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(dreamProp4
					init:
					view: 345
					setLoop: 1
					posn: 168 118
					cycleSpeed: 10
					setPri: 14
					setCel: -1
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(patti
					init:
					view: 346
					posn: 209 140
					cycleSpeed: 8
					setPri: 13
					setLoop: 0
					setCel: -1
				)
				(dreamProp1
					init:
					view: 346
					setLoop: 2
					posn: 209 140
					setPri: 13
					setCel: -1
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(= ticks 60)
			)
			(1
				(TimePrint 320 18 #at -1 185 #width 280)
				(dreamProp4 setLoop: 1 cycleSpeed: 10 setCycle: Forward)
				(= seconds 2)
			)
			(2 (= seconds 2))
			(3
				(dreamProp2 cycleSpeed: 10 setCycle: EndLoop)
				(dreamProp4 setLoop: 2 cycleSpeed: 10 setCycle: EndLoop)
				(dreamProp3
					init:
					view: 346
					setLoop: 1
					posn: 208 104
					setPri: 14
					setCel: 0
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(dreamProp1 cycleSpeed: 10 setPri: 13 setCycle: EndLoop self)
				(zipperSound play:)
			)
			(4
				(dreamProp4 setLoop: 3 cycleSpeed: 10 setCycle: EndLoop)
				(patti setCycle: EndLoop)
				(dreamProp1 hide:)
				(dreamProp3 setPri: 14 cycleSpeed: 10 setCycle: EndLoop self)
			)
			(5
				(dreamProp1
					view: 346
					setLoop: 3
					posn: 209 140
					setCel: -1
					setCycle: Forward
					setPri: 14
					show:
				)
				(= cycles 30)
			)
			(6
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(= local1 0)
				(curRoom drawPic: (curRoom picture?))
				(body init: stopUpd:)
				(tray init: stopUpd:)
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine init:)
				)
				(patti dispose:)
				(dreamProp1 dispose:)
				(dreamProp2 dispose:)
				(dreamProp3 dispose:)
				(dreamProp4 dispose:)
				(theMusic number: 321 loop: -1 play:)
				(theMusic2 number: 346 loop: 1 play: 60 fade: 127 15 10 0)
				(arm
					init:
					view: 323
					setLoop: 1
					posn: 122 86
					setPri: 14
					ignoreActors: 1
					cycleSpeed: 10
					setScript: sSqeezeNuts
				)
				(arm2
					init:
					view: 323
					setLoop: 0
					posn: 138 78
					setPri: 5
					cycleSpeed: 10
					ignoreActors: 1
				)
				(larry
					init:
					view: 321
					loop: 1
					setCel: 1
					posn: 135 72
					setPri: 4
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(= seconds 3)
			)
			(7
				(TimePrint 320 19 #at -1 15 #width 280)
				(larry view: 325 setLoop: 1)
				(TimePrint 320 20 #at -1 185 #title {The Stewardess} #dispose self)
				(arm setScript: 0)
			)
			(8
				(larry setCel: 1 cycleSpeed: 10 setCycle: CycleTo 7 1 self)
			)
			(9 (= seconds 7))
			(10
				(arm
					view: 321
					setLoop: 2
					setCel: 0
					posn: 126 79
					setPri: 5
				)
				(arm2
					view: 321
					setLoop: 2
					setCel: 1
					posn: 173 84
					setPri: 5
				)
				(theMusic2 stop:)
				(= seconds 2)
			)
			(11
				(TimePrint 320 21 #at -1 185 #width 280)
				(theMusic fade: 0 15 10 1)
				(theMusic2 fade: 0 15 10 1)
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance sWakeUpVenice of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(SetFFRoom 310)
				(cloud hide:)
				(curRoom drawPic: (curRoom picture?))
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(curRoom overlay: 355)
				(dreamProp2
					init:
					view: 356
					setLoop: 0
					posn: 187 126
					cycleSpeed: 10
					setCel: 255
					setPri: 14
					setCycle: BegLoop self
				)
				(theMusic number: 350 loop: -1 play: 60 fade: 127 15 10 0)
				(HandsOff)
			)
			(1 (= ticks 60))
			(2
				(TimePrint 320 22 #at -1 15 #width 280 #dispose self)
			)
			(3
				(theMusic2
					number: 352
					loop: -1
					play: 60
					fade: 127 15 15 0
				)
				(= ticks 10)
			)
			(4
				(dreamProp1
					init:
					view: 355
					setLoop: 0
					setCycle: Forward
					setPri: 14
					posn: 205 137
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(= ticks 123)
			)
			(5
				(theMusic2 fade: 0 15 5 1 self)
			)
			(6
				(= local1 0)
				(curRoom drawPic: (curRoom picture?))
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine init:)
				)
				(cloud show:)
				(dreamProp1 dispose:)
				(dreamProp2 dispose:)
				(theMusic number: 321 loop: -1 play:)
				(theMusic2 number: 346 loop: 1 play: 60 fade: 127 15 10 0)
				(body
					init:
					view: 325
					loop: 0
					setCel: 0
					posn: 165 124
					setPri: 4
					stopUpd:
				)
				(arm
					init:
					view: 324
					setLoop: 0
					setCel: 2
					posn: 147 105
					setPri: 8
				)
				(arm2
					init:
					view: 324
					setLoop: 1
					setCel: 0
					posn: 169 82
					setPri: 6
				)
				(larry setPri: 5 startUpd:)
				(= ticks 10)
			)
			(7
				(arm2 cycleSpeed: 10 setCycle: EndLoop self)
			)
			(8
				(larry view: 324 setLoop: 0 setCel: 0 posn: 139 72)
				(= seconds 3)
			)
			(9
				(larry
					view: 325
					loop: 1
					setCel: 5
					posn: 134 70
					cycleSpeed: 17
					setCycle: CycleTo 7 1 self
				)
			)
			(10 (= seconds 3))
			(11
				(TimePrint 320 23 #at -1 15 #width 280 #dispose self)
			)
			(12 (= ticks 30))
			(13
				(TimePrint 320 21 #at -1 185 #width 280)
				(theMusic fade: 0 15 10 1)
				(theMusic2 fade: 0 15 10 1)
				(= seconds 2)
			)
			(14 (curRoom newRoom: 310))
		)
	)
)

(instance sDreamTaj of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine dispose:)
				)
				(body dispose:)
				(arm dispose:)
				(tray dispose:)
				(cloud dispose:)
				(arm2 dispose:)
				(dreamProp1
					view: 370
					setLoop: 0
					setCycle: RandCycle
					posn: 72 122
					cycleSpeed: 8
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(dreamProp2
					init:
					view: 370
					setLoop: 2
					posn: 155 80
					cycleSpeed: 3
					setCycle: RandCycle
				)
				(dreamProp3
					init:
					view: 370
					setLoop: 1
					setCycle: RandCycle
					cycleSpeed: 8
					posn: 236 112
				)
				(dreamProp4
					init:
					view: 370
					setLoop: 3
					setCycle: RandCycle
					cycleSpeed: 6
					posn: 126 88
				)
				(dreamProp5
					init:
					view: 370
					setLoop: 4
					setCycle: RandCycle
					cycleSpeed: 6
					posn: 193 94
				)
				(patti
					init:
					view: 371
					posn: 166 151
					setLoop: 0
					setCycle: Forward
					cycleSpeed: 8
					setScript: sPlayGuitar
				)
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(curRoom overlay: 370)
				(= seconds 3)
			)
			(1
				(TimePrint 320 24 #dispose self #at -1 15 #width 280)
			)
			(2 (= ticks 90))
			(3
				(theMusic fade: 0 15 10 1)
				(theMusic2 fade: 0 15 10 1)
				(= seconds 3)
			)
			(4 (= local1 0) (= cycles 2))
			(5 (curRoom newRoom: 800))
		)
	)
)

(instance sWakeUpTaj of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(SetFFRoom 310)
				(cloud hide:)
				(curRoom drawPic: (curRoom picture?))
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(curRoom overlay: 375)
				(dreamProp1
					init:
					view: 376
					setLoop: 0
					setCel: 0
					posn: 170 137
					setPri: 14
					cycleSpeed: 10
				)
				(larry
					init:
					view: 376
					setLoop: 2
					posn: 170 137
					cycleSpeed: 10
					setPri: 13
					stopUpd:
				)
				(dreamProp2
					init:
					view: 375
					setLoop: 0
					posn: 197 141
					setPri: 15
				)
				(patti
					init:
					view: 377
					setLoop: 0
					posn: 226 135
					setPri: 14
					cycleSpeed: 10
				)
				(= ticks 10)
			)
			(1
				(HandsOff)
				(TimePrint 320 25 #at -1 15 #width 280)
				(theMusic2 number: 375 loop: -1 play:)
				(patti cycleSpeed: 10 setCycle: Forward)
				(dreamProp2 cycleSpeed: 10 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(dreamProp2 setLoop: 1 setCycle: MyForward)
				(= seconds 3)
			)
			(3
				(dreamProp1 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(dreamProp1 setLoop: 1 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(TimePrint 320 26 #at -1 15 #width 280 #dispose)
				(theMusic2 fade: 0 15 12 1)
				(= seconds 4)
			)
			(6
				(= local1 0)
				(curRoom drawPic: (curRoom picture?))
				(body init: stopUpd:)
				(arm init:)
				(tray init:)
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine init:)
				)
				(dreamProp1 dispose:)
				(dreamProp2 dispose:)
				(patti dispose:)
				(theMusic number: 321 loop: -1 play:)
				(theMusic2 number: 346 loop: 1 play: 60 fade: 127 15 10 0)
				(cloud show:)
				(tray init: cycleSpeed: 10 setCycle: EndLoop self)
				(larry init: view: 325 posn: 136 68 setLoop: 1 setCel: 0)
				(body init: view: 325 setLoop: 0 setCel: 0 stopUpd:)
				(arm
					init:
					view: 325
					setLoop: 2
					setCel: 1
					posn: 132 76
					setPri: 6
				)
				(arm2
					init:
					view: 325
					setLoop: 3
					setCel: 1
					setPri: 6
					posn: 183 56
				)
			)
			(7
				(larry setCel: 1 cycleSpeed: 10 setCycle: CycleTo 5 1 self)
			)
			(8
				(larry setCel: 6 posn: 144 66)
				(body setCel: 1)
				(arm setCel: 2 posn: 129 78)
				(arm2 setCel: 2 posn: 170 60)
				(= ticks 30)
			)
			(9
				(arm setCel: 3 posn: 129 75)
				(larry setCel: 7 posn: 152 61)
				(body setCel: 2)
				(= seconds 3)
			)
			(10
				(TimePrint 320 21 #at -1 185 #width 280)
				(TimePrint 320 27 #at -1 185 #width 280)
				(theMusic fade: 0 15 10 1)
				(theMusic2 fade: 0 15 10 1)
				(= seconds 3)
			)
			(11 (curRoom newRoom: 310))
		)
	)
)

(instance sDreamVenice of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine dispose:)
				)
				(body dispose:)
				(arm dispose:)
				(tray dispose:)
				(cloud dispose:)
				(arm2 dispose:)
				(dreamProp1
					view: 350
					setLoop: 0
					posn: 216 93
					setPri: 12
					setCycle: Forward
					cycleSpeed: 10
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(dreamProp2
					init:
					view: 350
					setLoop: 1
					posn: 209 135
					setCycle: Forward
					cycleSpeed: 10
					setPri: 12
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(larry
					init:
					view: 351
					setLoop: 0
					setCel: 0
					posn: 297 77
					setPri: 13
					signal: (| ignrAct fixedLoop fixPriOn)
				)
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(curRoom overlay: 350)
				(= ticks 30)
			)
			(1
				(TimePrint 320 28 #at -1 185 #width 280)
				(larry
					cycleSpeed: 8
					moveSpeed: 12
					setCycle: Forward
					setMotion: MoveTo 285 83 self
				)
			)
			(2
				(larry setMotion: MoveTo 266 90 self)
			)
			(3
				(larry setMotion: MoveTo 255 96 self)
			)
			(4
				(larry setCel: 0)
				(= cycles 2)
			)
			(5
				(TimePrint 320 29 #at -1 20)
				(TimePrint 320 30 #at -1 185)
				(larry setLoop: 1 setCel: 0)
				(= ticks 30)
			)
			(6
				(theMusic2 number: 351 loop: 1 play:)
				(larry setCel: 1)
				(dreamProp3
					init:
					view: 351
					setPri: 14
					setLoop: 2
					posn: 241 98
				)
				(= cycles 2)
			)
			(7
				(larry
					cycleSpeed: 20
					setCycle: EndLoop
					setMotion: MoveTo 145 143 self
				)
			)
			(8
				(if modelessDialog (modelessDialog dispose:))
				(dreamProp1 dispose:)
				(dreamProp2 dispose:)
				(patti dispose:)
				(curRoom drawPic: (curRoom picture?))
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(larry
					setMotion: 0
					view: 321
					setLoop: 1
					setCel: 1
					posn: 134 70
					setPri: 4
					addToPic:
					dispose:
				)
				(curRoom overlay: 355)
				(dreamProp3
					init:
					view: 356
					setLoop: 0
					posn: 187 126
					cycleSpeed: 10
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(9
				(TimePrint 320 31 #at -1 15 #width 280)
				(= ticks 30)
			)
			(10
				(= local1 0)
				(theMusic fade: 0 15 10 1)
				(theMusic2 fade: 0 15 10 1)
				(= seconds 2)
			)
			(11 (curRoom newRoom: 600))
		)
	)
)

(instance theMagazine of Feature
	(properties
		x 206
		y 202
		z 100
		nsTop 92
		nsLeft 196
		nsBottom 112
		nsRight 216
		description {the magazine}
		sightAngle 40
	)
	
	(method (doVerb)
		(magazine doVerb: &rest)
	)
)

(instance sDreamCasa of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(if (and (not (Btst fPutMagazineOnTable)) (not (ego has: iMagazine)))
					(magazine dispose:)
				)
				(body dispose:)
				(arm dispose:)
				(tray dispose:)
				(cloud dispose:)
				(arm2 dispose:)
				(theMusic2 number: 380 loop: -1 play:)
				(dreamProp1
					init:
					view: 380
					setLoop: 0
					posn: 228 47
					setPri: 14
					show:
					addToPic:
				)
				(dreamProp2
					init:
					view: 380
					setLoop: 1
					posn: 267 49
					setPri: 14
					show:
					addToPic:
				)
				(Graph GFillRect 0 0 192 320 2 0 0)
				(= local2 56)
				(curRoom overlay: 380)
				(= seconds 4)
			)
			(1
				(Say Leisure_Suit_Bogie 320 32 #dispose #caller self)
			)
			(2 (= seconds 3))
			(3
				(Say Ingrid_Patti 320 33 #dispose #caller self)
			)
			(4 (= seconds 3))
			(5
				(Say Ingrid_Patti 320 34 #dispose #caller self)
			)
			(6 (= seconds 3))
			(7
				(Say Leisure_Suit_Bogie 320 35 #dispose #caller self)
			)
			(8
				(theMusic fade: 0 15 10 1)
				(= local1 0)
				(= ticks 10)
			)
			(9 (curRoom newRoom: 380))
		)
	)
)

(instance Leisure_Suit_Bogie of Talker
	(properties
		x 1
		y 180
		nsTop 83
		nsLeft 125
		view 1381
		loop 1
		talkWidth 180
		name "Leisure Suit Bogie"
	)
	
	(method (init)
		(= mouth hisMouth)
		(super init:)
	)
)

(instance hisMouth of Prop
	(properties
		view 1381
	)
)

(instance Ingrid_Patti of Talker
	(properties
		x 140
		y 180
		nsTop 82
		nsLeft 169
		view 1382
		loop 1
		talkWidth 180
		name "Ingrid Patti"
	)
	
	(method (init)
		(= mouth herMouth)
		(super init:)
	)
)

(instance herMouth of Prop
	(properties
		view 1382
	)
)

(instance theMusic3 of Sound
	(properties
		number 322
	)
)

(instance zipperSound of Sound
	(properties
		number 347
	)
)

(instance sPlayGuitar of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dreamProp1 cycleSpeed: (Random 3 8))
				(dreamProp2 cycleSpeed: (Random 3 8))
				(dreamProp3 cycleSpeed: (Random 3 8))
				(dreamProp4 cycleSpeed: (Random 3 8))
				(dreamProp5 cycleSpeed: (Random 3 8))
				(patti setLoop: 0 cycleSpeed: (Random 3 8) setCycle: Forward)
				(= ticks (Random 20 40))
			)
			(1
				(patti
					setLoop: 1
					cycleSpeed: (Random 3 8)
					setCycle: EndLoop self
				)
			)
			(2
				(patti setLoop: 2 cycleSpeed: (Random 3 8) setCycle: Forward)
				(= ticks (Random 20 40))
			)
			(3
				(patti
					setLoop: 1
					cycleSpeed: (Random 3 8)
					setCycle: BegLoop self
				)
			)
			(4 (self init:))
		)
	)
)
