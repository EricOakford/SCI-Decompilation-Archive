;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh) (include menu.sh)
(use Main)
(use LoadMany)
(use Gauge)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	openCredits 0
)

(local
	local0
	titleYStep
	oldSpeed
)
(instance openCredits of Room
	(properties
		picture 900
		style IRISOUT
	)
	
	(method (init)
		(HandsOff)
		(LoadMany VIEW 901 902 903 904 905 906)
		(Load SOUND 1)
		(super init:)
		(= oldSpeed speed)
		(= titleYStep
			(cond 
				((CheckHowFast) 1)
				((CheckHowFast 1) 3)
				((CheckHowFast 0) 6)
			)
		)
		((ScriptID 0 23) prevSignal: 0)
		(StatusLine disable: state: FALSE)
		(SetCursor theCursor FALSE)
		(TheMenuBar hide: state: FALSE)
		(self setScript: openingCredits)
	)
)

(instance openingCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= speed 2)	;EO: Changed to fix super-fast credits
				;(= speed 0)
				((ScriptID 0 23) number: 1 loop: 1 play:)
				(= cycles 1)
			)
			(1
				(titleCredit
					view: 901
					loop: 0
					cel: 0
					posn: 157 195
					ignoreHorizon:
					ignoreActors:
					yStep: titleYStep
					illegalBits: 0
					init:
					setMotion: MoveTo 157 50
				)
				(nameCredit
					view: 901
					loop: 1
					cel: 0
					posn: 158 240
					ignoreHorizon:
					ignoreActors:
					yStep: titleYStep
					illegalBits: 0
					init:
					setMotion: MoveTo 158 50 self
				)
			)
			(2
				(titleCredit
					view: 902
					posn: 157 195
					setMotion: MoveTo 157 50
				)
				(nameCredit
					view: 902
					posn: 158 240
					setMotion: MoveTo 158 50 self
				)
			)
			(3
				(titleCredit
					view: 903
					posn: 159 195
					setMotion: MoveTo 159 50
				)
				(nameCredit
					view: 903
					posn: 158 240
					setMotion: MoveTo 158 50 self
				)
			)
			(4
				(titleCredit
					view: 904
					posn: 161 195
					setMotion: MoveTo 161 50
				)
				(nameCredit
					view: 904
					posn: 160 240
					setMotion: MoveTo 160 50 self
				)
			)
			(5
				(titleCredit
					view: 905
					posn: 159 195
					setMotion: MoveTo 159 50
				)
				(nameCredit
					view: 905
					posn: 159 220
					setMotion: MoveTo 159 50 self
				)
			)
			(6
				(titleCredit
					view: 906
					posn: 158 195
					setMotion: MoveTo 158 50
				)
				(nameCredit
					view: 906
					posn: 159 220
					setMotion: MoveTo 159 50 self
				)
			)
			(7
				(if (== ((ScriptID 0 23) prevSignal?) 10)
					(= cycles 1)
				else
					(titleCredit dispose:)
					(nameCredit dispose:)
					((ScriptID 0 23) loop: 1 fade:)
					(= cycles 5)
				)
			)
			(8
				(theGame setSpeed: oldSpeed)
				(curRoom newRoom: 86)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(super handleEvent: event)
		(if (== (event message?) ENTER)
			((ScriptID 0 23) fade:)
			(theGame setSpeed: oldSpeed)
			(curRoom newRoom: 86)
			(event claimed: TRUE)
		)
		(if (== (event type?) evKEYBOARD)
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
				(else  (event claimed: TRUE))
			)
		)
	)
)

(instance titleCredit of Actor
	(properties)
)

(instance nameCredit of Actor
	(properties)
)
