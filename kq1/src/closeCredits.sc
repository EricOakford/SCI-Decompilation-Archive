;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include game.sh) (include menu.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use DCIcon)
(use Gauge)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	closeCredits 0
)

(local
	creditYStep
	oldSpeed
	songState
)
(procedure (ChangeEndMusic)
	(= songState
		(switch ((ScriptID 0 23) number?)
			(101 1)
			(102 2)
			(103 3)
		)
	)
)

(instance closeCredits of Room
	(properties
		picture 900
		style HSHUTTER
	)
	
	(method (init)
		(LoadMany VIEW 907 908 909 910 911 912)
		(Bset fInCartoon)
		(super init:)
		(SetMenu invI p_state FALSE)
		(= oldSpeed speed)
		(= creditYStep
			(cond 
				((CheckHowFast) 1)
				((CheckHowFast 1) 3)
				((CheckHowFast 0) 6)
			)
		)
		(HandsOff)
		(StatusLine disable: state: FALSE)
		(TheMenuBar hide: state: TRUE)
		(SetCursor theCursor FALSE)
		(ChangeEndMusic)
		(self setScript: closingCredits)
	)
)

(instance closingCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= speed 0)
				(if (not script) (self setScript: endSongScript))
				(animators
					posn: 156 48
					yStep: creditYStep
					setMotion: MoveTo 156 65
					init:
				)
				(JEFFCROWE
					posn: 158 170
					yStep: creditYStep
					setMotion: MoveTo 158 64
					init:
				)
				(CHERILOYD
					posn: 157 182
					yStep: creditYStep
					setMotion: MoveTo 157 64 self
					init:
				)
			)
			(1
				(JEFFCROWE dispose:)
				(CHERILOYD dispose:)
				(animators setMotion: MoveTo 156 48 self)
			)
			(2
				(animators dispose:)
				(backgroundArtists
					posn: 158 48
					yStep: creditYStep
					setMotion: MoveTo 158 65
					init:
				)
				(CINDYWALKER
					posn: 158 170
					yStep: creditYStep
					setMotion: MoveTo 158 64
					init:
				)
				(JEFFCROWE2
					posn: 157 182
					yStep: creditYStep
					setMotion: MoveTo 157 64
					init:
				)
				(JENNIFER
					posn: 156 194
					yStep: creditYStep
					setMotion: MoveTo 156 64 self
					init:
				)
			)
			(3
				(JENNIFER dispose:)
				(JEFFCROWE2 dispose:)
				(CINDYWALKER dispose:)
				(backgroundArtists setMotion: MoveTo 158 48 self)
			)
			(4
				(backgroundArtists dispose:)
				(programmers
					posn: 159 48
					yStep: creditYStep
					setMotion: MoveTo 159 65
					init:
				)
				(GARYK
					posn: 160 170
					yStep: creditYStep
					setMotion: MoveTo 160 64
					init:
				)
				(RANDY
					posn: 159 182
					yStep: creditYStep
					setMotion: MoveTo 159 64
					init:
				)
				(MARKWILDEN
					posn: 159 194
					yStep: creditYStep
					setMotion: MoveTo 159 64 self
					init:
				)
			)
			(5
				(MARKWILDEN dispose:)
				(RANDY dispose:)
				(GARYK dispose:)
				(programmers setMotion: MoveTo 159 48 self)
			)
			(6
				(programmers dispose:)
				(development
					posn: 158 48
					yStep: creditYStep
					setMotion: MoveTo 158 65
					init:
				)
				(SET1
					posn: 156 194
					yStep: creditYStep
					setMotion: MoveTo 156 64
					init:
				)
				(SET2
					posn: 155 227
					yStep: creditYStep
					setMotion: MoveTo 155 64
					init:
				)
				(SET3
					posn: 156 260
					yStep: creditYStep
					setMotion: MoveTo 156 64
					init:
				)
				(MARKHOOD
					posn: 154 272
					yStep: creditYStep
					setMotion: MoveTo 154 64
					init:
				)
				(ERICHART
					posn: 156 284
					yStep: creditYStep
					setMotion: MoveTo 156 64 self
					init:
				)
			)
			(7
				(ERICHART dispose:)
				(MARKHOOD dispose:)
				(SET3 dispose:)
				(SET2 dispose:)
				(SET1 dispose:)
				(development setMotion: MoveTo 158 48 self)
			)
			(8
				(development dispose:)
				(soundEffects
					posn: 161 48
					yStep: creditYStep
					setMotion: MoveTo 161 65
					init:
				)
				(KENALLEN2
					posn: 160 170
					yStep: creditYStep
					setMotion: MoveTo 160 64 self
					init:
				)
			)
			(9
				(KENALLEN2 dispose:)
				(soundEffects setMotion: MoveTo 161 48 self)
			)
			(10
				(soundEffects dispose:)
				(ELLEN
					posn: 160 180
					yStep: creditYStep
					setMotion: MoveTo 160 48 self
					init:
				)
			)
			(11
				(ELLEN dispose:)
				(self changeState: 0)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(super handleEvent: event)
		(if (== (event type?) keyDown)
			(switch (event message?)
				(`#2
					(if (GetMenu soundI p_value)
						(DoSound SoundOn FALSE)
						(SetMenu soundI p_value FALSE p_text {Turn on})
					else
						(DoSound SoundOn TRUE)
						(SetMenu soundI p_value TRUE p_text {Turn off})
					)
				)
				(`^s
					(= i
						((Gauge new:)
							description:
								{Use the mouse or right and left arrow keys to set the sound volume.}
							text: {Sound Volume}
							minimum: 0
							normal: 12
							maximum: 15
							higher: {Louder}
							lower: {Softer}
							doit: (DoSound ChangeVolume)
						)
					)
					(DoSound ChangeVolume i)
					(DisposeScript GAUGE)
				)
				(`#7
					(SetCursor theCursor TRUE)
					(theGame restore:)
				)
				(`#9
					(if
						(Print 85 0
							#title {Restart}
							#font SYSFONT
							#icon movingIcon
							#button {Restart} 1
							#button {Oops} 0
						)
						(theGame restart:)
					)
				)
				(`^q
					(= quit
						(Print 85 1
							#title {Quit}
							#font SYSFONT
							#icon movingIcon
							#button {Quit} 1
							#button {Oops} 0
						)
					)
				)
				(else  (event claimed: TRUE))
			)
		)
	)
)

(instance endSongScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if songState (self changeState: songState) else (= cycles 1))
			)
			(1
				(if (== songState 1)
					(if (== ((ScriptID 0 23) prevSignal?) -1)
						(= songState 2)
						((ScriptID 0 23) number: 102 loop: 1 play: self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(if (== songState 2)
					(if (== ((ScriptID 0 23) prevSignal?) -1)
						(= songState 1)
						((ScriptID 0 23) number: 103 loop: 1 play: self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(3 (self changeState: songState))
		)
	)
)

(instance animators of Actor
	(properties
		view 907
		signal (| ignrAct ignrHrz fixedCel fixedLoop)
	)
)

(instance JEFFCROWE of Actor
	(properties
		view 907
		loop 1
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance CHERILOYD of Actor
	(properties
		view 907
		loop 2
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance backgroundArtists of Actor
	(properties
		view 908
		signal (| ignrAct ignrHrz fixedCel fixedLoop)
	)
)

(instance CINDYWALKER of Actor
	(properties
		view 908
		loop 1
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance JEFFCROWE2 of Actor
	(properties
		view 908
		loop 2
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance JENNIFER of Actor
	(properties
		view 908
		loop 3
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance programmers of Actor
	(properties
		view 909
		signal (| ignrAct ignrHrz fixedCel fixedLoop)
	)
)

(instance GARYK of Actor
	(properties
		view 909
		loop 1
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance RANDY of Actor
	(properties
		view 909
		loop 2
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance MARKWILDEN of Actor
	(properties
		view 909
		loop 3
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance development of Actor
	(properties
		view 910
		signal (| ignrAct ignrHrz fixedCel fixedLoop)
	)
)

(instance SET1 of Actor
	(properties
		view 910
		loop 1
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance SET2 of Actor
	(properties
		view 910
		loop 2
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance SET3 of Actor
	(properties
		view 910
		loop 3
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance MARKHOOD of Actor
	(properties
		view 910
		loop 4
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance ERICHART of Actor
	(properties
		view 910
		loop 5
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance soundEffects of Actor
	(properties
		view 911
		signal (| ignrAct ignrHrz fixedCel fixedLoop)
	)
)

(instance KENALLEN2 of Actor
	(properties
		view 911
		loop 1
		priority 1
		signal (| ignrAct ignrHrz fixedCel fixedLoop fixPriOn)
	)
)

(instance ELLEN of Actor
	(properties
		view 912
		signal (| ignrAct ignrHrz fixedCel fixedLoop)
	)
)

(instance movingIcon of DCIcon
	(properties
		view 699
		loop 2
	)
	
	(method (init)
		(super init:)
		(self cycleSpeed: (if (> howFast 2) 10 else 6))
	)
)
