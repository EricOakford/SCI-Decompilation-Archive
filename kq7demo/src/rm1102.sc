;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1102)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Motion)
(use Actor)
(use System)

(public
	rm1102 0
)

(instance rm1102 of KQRoom
	(properties
		picture 1102
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
	)
	
	(method (init)
		(Bset 21)
		(super init:)
		(curRoom setScript: demo)
	)
)

(instance demo of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= seconds 1)
				(ego view: 1203 loop: 1 cel: 0 x: 142 y: 106 init:)
			)
			(1
				(theGame handsOff:)
				(Load RES_WAVE 829)
				(Load RES_WAVE 1207)
				(Load RES_SCRIPT -580)
				(if (not (Platform PlatWin))
					(theSoundFX number: 1211 setVol: 127 setLoop: 1 play:)
				)
				(jack
					view: 1203
					loop: 0
					cel: 0
					x: 142
					y: 106
					init:
					setCycle: EndLoop self
				)
				(ego setCycle: CycleTo 5 1 self)
			)
			(2 1)
			(3
				(jack loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4
				(if (not (Platform PlatWin))
					(theSoundFX number: 1211 setVol: 127 setLoop: 1 play:)
				)
				(jack loop: 3 cel: 0 posn: 187 105 setCycle: EndLoop self)
				(ego setCycle: CycleTo 11 1 self)
			)
			(5 1)
			(6
				(if (not (Platform PlatWin))
					(theSoundFX number: 1207 setVol: 127 setLoop: 1 play:)
				)
				(ego setCel: 12 setCycle: CycleTo 18 1 self)
				(jack hide:)
			)
			(7
				(ego setCel: 19)
				(if (not (Platform PlatWin))
					(theSoundFX number: 1211 setVol: 127 setLoop: 1 play:)
				)
				(jack
					cycleSpeed: 9
					loop: 4
					cel: 0
					posn: 141 107
					show:
					setCycle: EndLoop self
				)
				(ego setCycle: EndLoop)
			)
			(8
				(jack loop: 5 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(9
				(if (not (Platform PlatWin))
					(theSoundFX number: 829 setVol: 127 setLoop: 1 play:)
				)
				(jack loop: 5 cel: 4 setCycle: CycleTo 6 1 self)
			)
			(10
				(if (not (Platform PlatWin))
					(theSoundFX number: 829 setVol: 127 setLoop: 1 play:)
				)
				(jack cel: 4 setCycle: CycleTo 6 1 self)
			)
			(11
				(if (not (Platform PlatWin))
					(theSoundFX number: 829 setVol: 127 setLoop: 1 play:)
				)
				(jack cel: 4 setCycle: CycleTo 6 1 self)
			)
			(12
				(if (not (Platform PlatWin)) (theSoundFX stop:))
				(jack setCel: 7 setCycle: EndLoop self)
			)
			(13
				(jack dispose:)
				(if (not (Platform PlatWin))
					(theSoundFX number: 1208 setVol: 127 setLoop: 1 play:)
				)
				(= seconds 4)
			)
			(14
				(theSoundFX stop:)
				(= temp0 100)
				(while (>= temp0 0)
					(Palette PalIntensity 0 255 temp0)
					(FrameOut)
					(-= temp0 5)
				)
				(ego view: 8074 loop: 1 cel: 3 x: 130 y: 103)
				(= seconds 1)
			)
			(15
				(= temp0 0)
				(while (<= temp0 100)
					(Palette PalIntensity 0 255 temp0)
					(FrameOut)
					(+= temp0 5)
				)
				(Load RES_VIEW 8074)
				(Load RES_VIEW 1207)
				(Load RES_VIEW 8294)
				(= seconds 1)
			)
			(16
				(ego setCycle: CycleTo 0 -1 self)
			)
			(17
				(jack
					view: 1207
					loop: 0
					cel: 0
					x: 58
					y: 96
					init:
					setCycle: CycleTo 3 1 self
				)
			)
			(18
				(if (not (Platform PlatWin))
					(theSoundFX number: 830 setVol: 127 setLoop: 1 play:)
				)
				(jack setCycle: EndLoop self)
			)
			(19
				(glasses init:)
				(jack dispose:)
				(ego view: 8294 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(20 (= seconds 2))
			(21 (curRoom newRoom: 1450))
		)
	)
)

(instance glasses of View
	(properties
		x 58
		y 96
		view 1207
		loop 1
	)
)

(instance jack of Actor
	(properties
		x 80
		y 106
		view 1203
	)
)
