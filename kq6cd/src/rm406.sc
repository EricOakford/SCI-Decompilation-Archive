;;; Sierra Script 1.0 - (do not remove this comment)
(script# 406)
(include sci.sh)
(use Main)
(use rLab)
(use n402)
(use Kq6Procs)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm406 0
)

(local
	local0
	local1 =  10
)
(instance rm406 of LabRoom
	(properties
		south 400
		west 400
	)
	
	(method (init)
		((ScriptID 30 0) labCoords: 152)
		(if (and (== prevRoomNum 435) (not (Btst 1)))
			(LoadMany 128 391 392 393 432)
			(Load rsPALETTE 400)
			(curRoom picture: 98)
			(theMusic stop:)
			(super init:)
			((ScriptID 30 0) cue:)
			(ego
				normal: 0
				view: 433
				x: 165
				y: 95
				ignoreHorizon:
				actions: egoDoTinderBoxCode
				init:
			)
			(UnLoad 128 900)
			(curRoom setScript: timerMinotaurKillEgo)
		else
			(curRoom picture: 400)
			(proc402_4)
			(super init:)
			((ScriptID 30 3) init:)
			((ScriptID 30 5) addToPic:)
			((ScriptID 30 9) addToPic:)
			((ScriptID 30 0) initCrypt: 1)
			(if (== prevRoomNum 435)
				(curRoom setScript: fallDownInLight)
			else
				(curRoom setScript: (ScriptID 30 1))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20
				(if ((ScriptID 30 0) darkRoomLit?)
					(messager say: 3 20 37 1 0 400)
					1
				else
					(curRoom setScript: lightItUp)
					1
				)
			)
			(1
				(if ((ScriptID 30 0) darkRoomLit?)
					(super doVerb: theVerb &rest)
					1
				else
					(messager say: 2 1 36 1 0 400)
					1
				)
			)
			(2
				(if ((ScriptID 30 0) darkRoomLit?)
					(super doVerb: theVerb &rest)
					1
				else
					(messager say: 2 2 36 0 0 400)
					1
				)
			)
			(5
				(if ((ScriptID 30 0) darkRoomLit?)
					(super doVerb: theVerb &rest)
					1
				else
					(messager say: 2 5 36 1 0 400)
					1
				)
			)
			(else 
				(messager say: 3 0 36 1 0 400)
				1
			)
		)
	)
	
	(method (notify)
		((ScriptID 30 5) addToPic:)
		((ScriptID 30 9) addToPic:)
		((ScriptID 30 3) init: show:)
	)
	
	(method (scriptCheck &tmp temp0)
		(= temp0 0)
		(if (< local1 100)
			(messager say: 3 0 36 1 0 400)
		else
			(= temp0 1)
		)
		(return temp0)
	)
)

(instance bigEyes of View
	(properties
		view 433
		loop 6
		priority 15
		signal $4010
	)
)

(instance fallDownInLight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(soundFx2 number: 960 setLoop: 1 play:)
				(ego
					setSpeed: 6
					posn: 151 153
					view: 307
					loop: 4
					cel: 0
					normal: 0
					init:
				)
				(= cycles 8)
			)
			(1 (ego setCycle: EndLoop self))
			(2
				(ego posn: 181 157 reset: 5)
				(UnLoad 128 307)
				(= cycles 6)
			)
			(3
				(messager say: 1 0 34 0 self 400)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance lightItUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(soundFx2 number: 932 setLoop: 1 play:)
				(ego
					view: 391
					x: (+ (ego x?) 7)
					normal: 0
					setLoop: 2
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(messager say: 3 20 36 1 self 400)
			)
			(2
				(theGame givePoints: 2)
				(proc402_4)
				(ego
					view: 3931
					setLoop: 6
					cel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
				(UnLoad 128 391)
			)
			(3
				(ego
					view: 3931
					setLoop: 5
					cel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
			)
			(4
				(ego
					view: 392
					setLoop: 1
					cycleSpeed: 1
					setCycle: Walk
					setMotion: PolyPath 125 142 self
				)
				0
				(UnLoad 128 3931)
			)
			(5
				(messager say: 3 20 36 2 self 400)
			)
			(6
				(theGlobalSound fade: 0 10 10)
				(theMusic
					number: 400
					setLoop: -1
					setVol: 0
					play:
					fade: 127 10 10
				)
				(ego
					view: 432
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop
				)
				(UnLoad 128 392)
				(self setScript: shiftThePalette self)
			)
			(7
				(messager say: 3 20 36 3 self 400)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance shiftThePalette of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Palette palSET_FROM_RESOURCE 400)
				(curRoom picture: 400)
				(curRoom drawPic: 400 (if global169 15 else 100))
				(Palette palSET_INTENSITY 77 255 local1)
				(= ticks 4)
			)
			(1
				((ScriptID 30 5) addToPic:)
				((ScriptID 30 9) addToPic:)
				((ScriptID 30 3) init:)
				((ScriptID 30 0) darkRoomLit: 1 notify:)
				(= ticks 2)
			)
			(2
				(if (== local1 100)
					(= state (+ state 2))
					(self cue:)
				else
					(= ticks 2)
				)
			)
			(3
				(if (== local1 30)
					(ego posn: (+ (ego x?) 4) (- (ego y?) 2) reset: 1)
					(UnLoad 128 432)
				)
				(Palette palSET_INTENSITY 77 255 local1)
				(= ticks 2)
			)
			(4
				(if (< local1 100)
					(= local1 (+ local1 5))
					(= state (- state 2))
				)
				(self cue:)
			)
			(5 (self dispose:))
		)
	)
)

(instance timerMinotaurKillEgo of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego setStep: 3 15 setMotion: MoveTo 165 160 self)
			)
			(1
				(theMusic stop:)
				(theGlobalSound number: 405 setLoop: -1 play:)
				(ego hide:)
				(ShakeScreen 4 (Random 0 2))
				(= seconds 1)
			)
			(2
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init: 231 145 82 145 28 183 285 183
							yourself:
						)
				)
				(ego show: cycleSpeed: 46 setCycle: RandCycle)
				(= seconds 4)
			)
			(3
				(ego
					setLoop: 1
					setStep: 6 10
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop self
				)
			)
			(4
				(cond 
					((Btst 1) (messager say: 1 0 34 1 self 400))
					((== prevRoomNum 400) (self cue:))
					(else (messager say: 1 0 31 0 self 400))
				)
			)
			(5
				(if (and (== prevRoomNum 400) (not (Btst 1)))
					(messager say: 1 0 33 1 self 400)
				else
					(self cue:)
				)
			)
			(6
				(ego setLoop: 2 cycleSpeed: 24 setCycle: RandCycle)
				(= seconds 4)
			)
			(7
				(theGame handsOn:)
				(if (Btst 1)
					(self dispose:)
				else
					(self setScript: lookAround self)
				)
			)
			(8 (= cycles 4))
			(9
				(theGame handsOff:)
				(soundFx number: 401 setLoop: 1 play: self)
			)
			(10
				(soundFx2 number: 433 setLoop: 1 play:)
				(= cycles 10)
			)
			(11
				(messager say: 1 0 32 1 self 400)
			)
			(12
				(bigEyes posn: (ego x?) (- (ego y?) 48) init:)
				(ego hide:)
				(= seconds 2)
			)
			(13
				(messager say: 1 0 32 2 self 400)
			)
			(14
				(bigEyes dispose:)
				(UnLoad 128 433)
				(messager say: 1 0 32 3 self 400)
			)
			(15
				(theGlobalSound stop:)
				(theMusic number: 406 setLoop: -1 play:)
				(ego setLoop: 4 cycleSpeed: 0 cel: 0 show: setCycle: Forward)
				(= seconds 1)
			)
			(16
				(ego posn: (+ (ego x?) 30) (ego y?))
				(= seconds 1)
			)
			(17
				(ego posn: (- (ego x?) 30) (ego y?))
				(= seconds 1)
			)
			(18
				(ego posn: (+ (ego x?) 30) (ego y?))
				(= seconds 1)
			)
			(19
				(ego setLoop: 4 setCycle: EndLoop self)
			)
			(20
				(theMusic number: 430 setLoop: 1 play:)
				(ego
					setLoop: 5
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 5 1 self
				)
			)
			(21
				(theMusic number: 431 setLoop: 1 play: self)
				(ego setCycle: EndLoop self)
			)
			(22)
			(23
				(theMusic number: 431 setLoop: 1 play:)
				(ego setLoop: 7 cel: 0 cycleSpeed: 30 setCycle: EndLoop self)
			)
			(24 (EgoDead 26))
		)
	)
)

(instance lookAround of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== howFast 0)
					(= seconds 8)
				else
					(= seconds (Random 2 4))
				)
			)
			(1
				(ego setLoop: 3 cel: 0 cycleSpeed: 6 setCycle: EndLoop self)
			)
			(2
				(ego setLoop: 2 cel: 0 cycleSpeed: 48 setCycle: Forward)
				(if (< local0 4) (++ local0) (= state (- state 3)))
				(self cue:)
			)
			(3 (self dispose:))
		)
	)
)

(instance egoDoTinderBoxCode of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(switch theVerb
			(20
				(if ((ScriptID 30 0) darkRoomLit?)
					(messager say: 3 20 37 1 0 400)
				else
					(curRoom setScript: lightItUp)
				)
			)
			(else 
				(if (== (approachCode doit: theVerb) -32768)
					(messager say: 3 0 36 1 0 400)
				else
					(= temp0 0)
				)
			)
		)
		(return temp0)
	)
)
