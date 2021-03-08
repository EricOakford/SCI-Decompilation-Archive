;;; Sierra Script 1.0 - (do not remove this comment)
(script# 165)
(include game.sh) (include "165.shm")
(use Main)
(use Talker)
(use Scaler)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm165 0
	rogTalker 15
)

(local
	[shuttleLoop 80] = [0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 1 1 1 1 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2]
	[shuttleX 80] = [319 281 272 265 261 257 252 247 243 240 238 232 227 224 220 218 216 144 139 137 133 129 125 122 119 117 115 108 102 96 90 85 83 82 82 85 86 92 100 107 112 120 129 138 143 148 152 154 156 157 157 154 150 144 138 129 119 110 103 99 93 91 92 95 100 107 117 128 137 150 167 185 203 221 240 257 274 291 309 307]
	[shuttleY 80] = [163 105 93 87 84 82 80 78 76 75 74 72 71 70 68 68 68 65 63 63 64 66 68 72 76 82 90 99 105 109 111 112 112 112 112 111 113 115 116 115 114 111 107 102 98 94 91 89 87 86 86 87 88 89 91 93 93 92 88 85 83 81 81 79 76 73 69 64 60 55 49 43 39 36 34 33 33 33 34 62]
	[shuttleScale 80] = [128 51 32 23 20 34 30 26 26 23 23 19 19 19 15 15 15 29 29 29 29 37 37 44 44 52 52 60 60 60 60 60 60 60 60 60 60 60 60 52 52 44 44 37 37 29 29 29 29 29 29 29 29 37 37 44 44 44 23 23 19 19 19 19 15 15 15 11 11 11 11 11 11 11 11 11 11 11 11 3]
	i
	local321 =  79
)
(instance rm165 of Room
	(properties
		picture 37
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(LoadMany RES_VIEW 164)
		(theDoors init:)
		(super init:)
		(curRoom setScript: sDoAll)
	)
)

(instance sDoAll of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(theMusic2 number: 106 setLoop: 1 play:)
				(theDoors setCycle: EndLoop self)
			)
			(2
				(theDoors dispose:)
				(= seconds 2)
			)
			(3 (messager say: N_ROOM NULL NULL 1 self))
			(4
				(Palette PALLoad 37 2)
				(PalVary PALVARYKILL)
				(PalVary PALVARYSTART 372 1)
				(= i 5)
				(shuttle1
					view: 164
					setLoop: -1
					setLoop: 0
					cel: 0
					x: 319
					y: 163
					init:
					setScale: Scaler 150 20 163 84
					setMotion: MoveTo 261 84 self
				)
				(theMusic2 number: 156 loop: -1 play: fade: 10 10 5 0)
			)
			(5
				(shuttle1
					loop: [shuttleLoop i]
					x: [shuttleX i]
					y: [shuttleY i]
					setScale: 0
					scaleSignal: 1
					scaleX: [shuttleScale i]
					scaleY: [shuttleScale i]
					show:
				)
				(if (> (++ i) local321)
					(= cycles 1)
				else
					(-- state)
					(switch i
						(17
							(shuttle1 hide:)
							(= seconds 3)
						)
						(50
							(messager say: N_ROOM NULL NULL 2 3 self)
						)
						(else  (= ticks 7))
					)
				)
			)
			(6
				(ego get: iCommunicator)
				(theMusic1 stop:)
				(theMusic2 stop:)
				(shuttle1 dispose:)
				(PalVary PALVARYKILL)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance theDoors of Prop
	(properties
		x 33
		y 21
		view 164
		loop 4
	)
)

(instance shuttle1 of Actor
	(properties
		x 319
		y 163
		view 164
		loop 3
		moveSpeed 2
	)
)

(instance rogTalker of Narrator
	(properties)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 170
			tailY: 65
			xOffset: 1
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)
