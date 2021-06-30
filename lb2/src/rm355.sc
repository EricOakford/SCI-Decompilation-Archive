;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include game.sh) (include "355.shm")
(use Main)
(use LBRoom)
(use Inset)
(use Talker)
(use Scaler)
(use Osc)
(use PolyPath)
(use StopWalk)
(use Actor)
(use System)

(public
	rm355 0
	tkrLaura 2
	tkrErnie 23
)

(local
	local0
)
(instance rm355 of LBRoom
	(properties
		noun N_ROOM
		picture 350
	)
	
	(method (init)
		(ego init: normalize: 831 setScale: Scaler 95 0 190 0)
		(self setRegions: 90)
		(Palette PALIntensity 0 255 0)
		(self setScript: sPartysOver)
		(super init:)
	)
)

(instance sPartysOver of Script

	(method (doit)
		(if (< local0 100)
			(Palette PALIntensity 0 255 (= local0 (+ local0 2)))
			(if (== local0 100) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 245 165 loop: 8 setCel: 5)
				((ScriptID 32 0)
					moveTo: 355
					originalView: 814
					init:
					setLoop: 8
					setCel: 2
					posn: 190 175
					ignoreActors: 1
				)
				((ScriptID 90 3)
					moveTo: 355
					view: 353
					setLoop: 1
					posn: 204 185
					ignoreActors: 1
				)
				((ScriptID 90 5)
					moveTo: 355
					view: 815
					posn: 147 184
					ignoreActors: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 184 184 self
				)
				((ScriptID 90 1)
					moveTo: 355
					view: 813
					posn: 119 184
					ignoreActors: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 161 184
				)
				((ScriptID 90 6)
					moveTo: 355
					view: 817
					posn: 85 184
					ignoreActors: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 132 184
				)
				(= cycles 1)
			)
			(1
				((ScriptID 32 0) addToPic:)
				(theMusic number: 350 setLoop: -1 flags: 1 play:)
			)
			(2 0)
			(3
				(messager say: 2)
				((ScriptID 90 3) setCycle: Oscillate 2 self)
			)
			(4
				((ScriptID 22 0) doit: -24576 self)
			)
			(5
				((ScriptID 90 5) setMotion: PolyPath 350 182)
				((ScriptID 90 1) setMotion: PolyPath 184 184 self)
				((ScriptID 90 6) setMotion: PolyPath 146 184)
			)
			(6
				((ScriptID 90 3) setCycle: Oscillate 3 self)
			)
			(7
				((ScriptID 90 1) setMotion: PolyPath 350 182)
				((ScriptID 90 6) setMotion: PolyPath 184 184 self)
			)
			(8
				((ScriptID 90 3) setCycle: Oscillate 2 self)
			)
			(9
				((ScriptID 90 6) setMotion: PolyPath 350 182 self)
			)
			(10
				((ScriptID 90 6) dispose:)
				((ScriptID 90 5) dispose:)
				((ScriptID 90 1) dispose:)
				(Bset 42)
				(theMusic fade:)
				(= ticks 60)
			)
			(11
				(theMusic number: 642 loop: -1 flags: 1 play:)
				((ScriptID 31 0)
					room: curRoomNum
					init:
					posn: 350 182
					setCycle: StopWalk -1
					setMotion: PolyPath (+ (ego x?) 10) (+ (ego y?) 10) self
				)
			)
			(12 (= cycles 2))
			(13
				(curRoom setInset: inErnie_Laura)
				(= cycles 2)
			)
			(14
				(messager say: 4 0 1 0 self)
			)
			(15
				(Bset 91)
				(curRoom setInset: 0)
				((ScriptID 32 0)
					posn: (- ((ScriptID 90 3) x?) 25) (- ((ScriptID 90 3) y?) 10)
					setLoop: 8
					setCel: 4
				)
				((ScriptID 21 0) doit: 267)
				((ScriptID 31 0) setMotion: PolyPath 350 185 self)
				(theMusic fade:)
			)
			(16
				(messager say: 1 0 0 0 self)
			)
			(17
				(WrapMusic init: 1 90 91 92 93)
				(ego setMotion: PolyPath 340 185 self)
			)
			(18 (curRoom newRoom: 370))
		)
	)
)

(instance sIllegal of Script
	(properties)
	
	(method (doit)
		(if (< local0 100)
			(Palette PALIntensity 0 255 (+= local0 2))
			(if (== local0 100) (self cue:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(Bset 91)
				(= currentAct 0)
				(user canInput: 0 canControl: 0)
				((ScriptID 32 0)
					room: curRoomNum
					originalView: 814
					init:
					setLoop: 8
					setCel: 2
					posn: 190 175
				)
				((ScriptID 90 3)
					room: curRoomNum
					view: 819
					setLoop: 8
					setCel: 2
					posn: 210 180
				)
				(ego posn: 230 182 setLoop: 8 setCel: 2)
			)
			(1 (messager say: 3 0 0 0 self))
			(2 (= seconds 5))
			(3
				(= deathReason 3)
				(curRoom newRoom: 99)
			)
		)
	)
)

(instance inErnie_Laura of Inset
	(properties
		picture 475
		hideTheCast 1
		name "inErnie&Laura"
	)
)

(instance tkrErnie of Talker
	(properties
		view 1475
		loop 1
		talkWidth 250
		back 15
		textX 20
		textY 120
	)
	
	(method (init)
		(= font userFont)
		(super init: 0 ernieEyes ernieMouth &rest)
	)
)

(instance ernieMouth of Prop
	(properties
		nsTop 78
		nsLeft 119
		view 1475
	)
)

(instance ernieEyes of Prop
	(properties
		nsTop 67
		nsLeft 122
		view 1475
		loop 2
	)
)

(instance tkrLaura of Narrator
	(properties
		x 150
		y 130
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)
