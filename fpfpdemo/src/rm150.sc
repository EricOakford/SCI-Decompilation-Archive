;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh) (include "150.shm")
(use Main)
(use MoveCyc)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm150 0
)

(local
	local0
	sequence
	theBalladSongPrevSignal
	local3
	local4
	local5 =  85
	underbits =  1
	local7 =  1
	local8 =  8
	local9 =  12
	local10 =  15
	[ballCycle 25] = [0 0 0 126 0 1 0 121 0 2 0 118 0 3 0 121 0 4 0 126 0 5 0 130 -32768]
	[local36 204] = [129 179 226 246 104 143 194 251 113 151 189 210 95 156 226 226 123 177 218 237 105 143 195 227 44 68 118 143 196 233 269 269 107 125 125 151 197 217 217 217 79 98 119 165 193 218 241 84 124 147 147 174 217 239 239 239 114 131 149 191 208 208 208 208 208 208 208 208 141 168 202 220 96 151 204 265 30 62 112 142 201 245 290 290 118 171 218 240 102 155 200 237 43 72 108 138 188 222 266 266 107 125 125 151 197 217 217 217 69 89 109 157 179 206 239 84 124 147 147 174 217 239 239 239 99 131 152 193 217 217 217 217 217 217 217 217 131 176 215 239 95 134 194 244 49 70 107 125 157 196 267 267 138 184 223 238 116 147 177 220 47 80 123 144 193 239 273 273 107 125 125 151 197 217 217 217 107 120 135 180 194 202 209 84 124 147 147 174 217 239 239 239 103 121 139 181 212 212 212 212 212 212 212 350]
)
(procedure (localproc_0389)
	(if (<= howFast local9)
		(vignette setLoop: (+ (vignette loop?) 1))
	else
		(vignette1 moveSpeed: 0 setMotion: MoveTo 290 80)
		(vignette
			setLoop: (+ (vignette1 loop?) 1)
			moveSpeed: 0
			setMotion: MoveTo 160 80
		)
	)
)

(procedure (localproc_03e6)
	(if (<= howFast local9)
		(vignette setLoop: (+ (vignette loop?) 1))
	else
		(vignette moveSpeed: 0 setMotion: MoveTo 30 80)
		(vignette1
			setLoop: (+ (vignette loop?) 1)
			moveSpeed: 0
			setMotion: MoveTo 160 80
		)
	)
)

(instance rm150 of Room
	(properties
		picture 150
		style FADEOUT
	)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(theIconBar disable:)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(super init:)
		(balladText init:)
		(if (> howFast local9)
			(vignette1 init: hide:)
		)
		(vignette init: hide:)
		(balladSong
			number: (if (> numVoices 11) 151 else 1151)
			flags: 1
			loop: 1
			signal: 0
			prevSignal: 0
			play: self
		)
	)
	
	(method (doit &tmp balladSongPrevSignal [str 64])
		(super doit:)
		(cond 
			(
				(==
					(= balladSongPrevSignal (balladSong prevSignal?))
					theBalladSongPrevSignal
				)
				0
			)
			(
				(OneOf balladSongPrevSignal
					143 147 151 155 159 163 167 175 183 190 199
					211 215 219 227 231 235 243 251 258 267 279
					283 287 295 299 303 311 319 326 335
				)
				(= theBalladSongPrevSignal balladSongPrevSignal)
				(++ sequence)
				(Display {} p_restore underbits)
				(Message MsgGet 150 N_ROOM 0 0 sequence @str)
				(= underbits
					(Display @str
						p_at 35 137
						p_color 41
						p_font 150
						p_mode teJustCenter
						p_width 320
						p_save
					)
				)
				(cond 
					((== balladSongPrevSignal 143)
						(cond 
							((<= howFast local8) 0)
							((>= howFast local10) (bouncingBall moveSpeed: 4 cycleSpeed: 4 init:))
							(else (bouncingBall init:))
						)
						(if (<= howFast local9)
							(vignette x: 160 y: 80 show:)
						else
							(vignette
								setLoop: 0
								moveSpeed: 5
								setMotion: MoveTo 160 80
								show:
							)
							(vignette1 show:)
						)
					)
					((OneOf balladSongPrevSignal 159 211 243 295) (localproc_03e6))
					((OneOf balladSongPrevSignal 175 227 279 311) (localproc_0389))
				)
			)
			((== balladSongPrevSignal 142) (balladText dispose:))
			((== balladSongPrevSignal 347)
				(if (<= howFast local9)
					(vignette hide:)
				else
					(vignette setMotion: MoveTo 280 80)
				)
				(Display {} p_restore underbits)
			)
			((== balladSongPrevSignal -1) (curRoom newRoom: 100))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(if (and (event type?) (== curRoomNum newRoomNum))
			(balladSong pause: TRUE)
			(event claimed: TRUE)
			(= timeOver 0)
			(theGame setCursor: ARROW_CURSOR TRUE)
			(curRoom newRoom: 105)
		)
	)
	
	(method (cue &tmp [temp0 8])
		(if
			(and
				(> (balladSong prevSignal?) 142)
				(<= (balladSong prevSignal?) 347)
				(> howFast local8)
			)
			(= local4 (- (= local3 [local36 local0]) local5))
			(= [ballCycle 2] (+ local5 (/ local4 10)))
			(= [ballCycle 6] (+ local5 (/ (* local4 3) 10)))
			(= [ballCycle 10] (+ local5 (/ local4 2)))
			(= [ballCycle 14] (- local3 (/ (* local4 3) 10)))
			(= [ballCycle 18] (- local3 (/ local4 10)))
			(= [ballCycle 22] local3)
			(bouncingBall setCycle: MoveCycle @ballCycle)
			(++ local0)
			(= local5 local3)
		)
	)
)

(instance vignette of Actor
	(properties
		x 30
		y 80
		view 120
		priority 6
		signal (| ignrAct fixPriOn)
		xStep 15
		moveSpeed 0
	)
)

(instance vignette1 of Actor
	(properties
		x 290
		y 80
		view 120
		priority 6
		signal (| ignrAct fixPriOn)
		xStep 15
		moveSpeed 0
	)
)

(instance bouncingBall of Actor
	(properties
		x 85
		y 130
		view 160
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 0
		moveSpeed 0
	)
)

(instance balladText of View
	(properties
		x 160
		y 90
		view 160
		loop 1
		priority 13
		signal fixPriOn
	)
)

(instance balladSong of Sound
	(properties
		flags mNOPAUSE
		number 151
		loop 0
	)
)
