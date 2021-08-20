;;; Sierra Script 1.0 - (do not remove this comment)
(script# PANEL)
(include game.sh)
(use Main)
(use Intrface)
(use ShowMap)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	controlPanel 0
)

(local
	local0
	local1
)
(procedure (DisplayNumbers theString theX theY &tmp [str 50] theColor)
	(= theColor (if (< numColors 10) vWHITE else vLRED))
	(Display
		(Format @str 27 0 theString)
		p_at theX theY
		p_color theColor
		p_at teJustRight
		p_width 25
		p_font 100
		p_save
	)
)

(instance controlPanel of Room
	(properties
		picture 27
		horizon 1
	)
	
	(method (init)
		(Load FONT 100)
		(Load SOUND (SoundFX 54))
		(super init:)
		(ShowMap)
		(= global135 (Random 10 23))
		(= global134 (Random 10 40))
		(DisplayNumbers 35 245 36)
		(DisplayNumbers 15 245 46)
		(DisplayNumbers 10 245 66)
		(DisplayNumbers 100 245 77)
		(DisplayNumbers 21 231 56)
		(DisplayNumbers 36 245 56)
		(DisplayNumbers 167 77 90)
		(DisplayNumbers 30 124 108)
		(DisplayNumbers 30 157 108)
		(if howFast
			(scanPanelBlip init: setScript: scanLightsScript)
		)
		(mBut init:)
		(bBut init:)
		(bBORide init:)
		(strnBORide init:)
		(pbORide init:)
		(sbORide init:)
		(hatch1Ind init:)
		(rpmLights init:)
		(sonarSwitch init:)
		(silentSwitch init:)
		(eBalSwitch init:)
		(compassNeedle init:)
		(tV init:)
		(missileSelector init: hide:)
		(sqSon init: setCel: 16)
		(bBalLights init:)
		(strnBalLights init:)
		(pBalLights init:)
		(sBalLights init:)
		(bowPIndBot init:)
		(bowPIndTop init:)
		(sternPIndTop init:)
		(sternPIndBot init:)
		(silentRunInd init:)
		(eBalGauge init:)
		(positionBlip init:)
		(diveIndLights init: stopUpd:)
		(wheel init:)
		(vSLine1 init: setScript: vSonarScript)
		(vSLine2 init:)
		(bankTurnInd init:)
		(diveLever init: posn: 277 144)
		(engineLever init:)
		(fCPanel init:)
		(if
		(not (if (positionBlip x?) (positionBlip y?)))
			(positionBlip dispose:)
		)
		(self setScript: simulatedSubSimulatorSimulation)
	)
)

(instance engSnd of Sound
	(properties
		number 54
		priority 5
	)
)

(instance sonarSwitch of View
	(properties
		y 159
		x 193
		view 227
		loop 3
		cel 3
	)
)

(instance sLMoveTo of MoveTo
	(properties)
)

(instance scanPanelBlip of Actor
	(properties
		y 129
		x 123
		view 27
		loop 5
		cel 5
		priority 12
		signal (| ignrAct fixedLoop fixPriOn)
		illegalBits $0000
		xStep 1
	)
)

(instance scanLightsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setMotion: sLMoveTo 186 (client y?) self)
			)
			(1
				(client posn: 123 129)
				(self init:)
			)
		)
	)
)

(instance mBut of View
	(properties
		y 144
		x 117
		view 27
		loop 5
		priority 11
		signal (| stopUpdOn fixPriOn staticView)
	)
)

(instance bBut of View
	(properties
		y 156
		x 127
		view 27
		loop 5
	)
)

(instance bBORide of View
	(properties
		y 112
		x 226
		view 127
		loop 5
		cel 1
		priority 11
	)
)

(instance strnBORide of View
	(properties
		y 117
		x 222
		view 127
		loop 5
		cel 1
		priority 11
	)
)

(instance pbORide of View
	(properties
		y 122
		x 218
		view 127
		loop 5
		cel 1
		priority 11
	)
)

(instance sbORide of View
	(properties
		y 127
		x 214
		view 127
		loop 5
		cel 1
		priority 11
	)
)

(instance rpmLights of View
	(properties
		y 167
		x 205
		view 427
		loop 4
		cel 1
	)
)

(instance hatch1Ind of View
	(properties
		y 142
		x 38
		view 427
		loop 3
		cel 4
		priority 12
		signal (| stopUpdOn fixPriOn staticView)
	)
)

(instance silentSwitch of View
	(properties
		y 157
		x 204
		view 227
		loop 3
		cel 1
	)
)

(instance eBalSwitch of View
	(properties
		y 163
		x 29
		view 427
		loop 5
		cel 1
		priority 12
		signal (| stopUpdOn fixPriOn staticView)
	)
)

(instance compassNeedle of View
	(properties
		y 123
		x 83
		view 127
		cel 3
		priority 13
		signal (| fixPriOn stopUpdOn)
	)
)

(instance tV of View
	(properties
		y 98
		x 157
		view 527
		loop 3
		cel 4
		priority 13
		signal (| ignrAct stopUpdOn fixPriOn staticView)
	)
)

(instance missileSelector of View
	(properties
		y 90
		x 152
		view 427
		loop 2
		priority 14
		signal (| stopUpdOn fixPriOn staticView)
	)
)

(instance sSEndLoop of EndLoop
	(properties)
)

(instance sqSon of Prop
	(properties
		y 84
		x 210
		view 27
		priority 12
		signal (| fixPriOn stopUpdOn)
	)
	
	(method (cue)
		(self cel: 0 setCycle: sSEndLoop)
	)
)

(instance bBalLights of Prop
	(properties
		y 117
		x 236
		view 127
		loop 6
		priority 12
		signal fixPriOn
		cycleSpeed 10
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance strnBalLights of Prop
	(properties
		y 122
		x 232
		view 127
		loop 6
		priority 12
		signal fixPriOn
		cycleSpeed 10
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance pBalLights of Prop
	(properties
		y 127
		x 228
		view 127
		loop 6
		priority 12
		signal fixPriOn
		cycleSpeed 10
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance sBalLights of Prop
	(properties
		y 132
		x 224
		view 127
		loop 6
		priority 12
		signal fixPriOn
		cycleSpeed 10
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance bowPIndBot of Prop
	(properties
		y 117
		x 104
		view 27
		loop 7
		cel 1
		priority 12
		signal (| fixPriOn stopUpdOn)
	)
)

(instance bowPIndTop of Prop
	(properties
		y 115
		x 101
		view 27
		loop 6
		cel 1
		priority 12
		signal (| fixPriOn stopUpdOn)
	)
)

(instance sternPIndBot of Prop
	(properties
		y 117
		x 201
		view 27
		loop 9
		cel 1
		priority 12
		signal (| fixPriOn stopUpdOn)
	)
)

(instance sternPIndTop of Prop
	(properties
		y 115
		x 202
		view 27
		loop 8
		cel 1
		priority 12
		signal fixPriOn
	)
)

(instance silentRunInd of Prop
	(properties
		y 161
		x 196
		view 227
		loop 4
		priority 12
		cycleSpeed 2
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance diveIndLights of Prop
	(properties
		y 180
		x 285
		view 227
		loop 5
		cycleSpeed 3
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance eBalGauge of Prop
	(properties
		y 156
		x 25
		view 427
		loop 6
		priority 12
		cycleSpeed 8
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance positionBlip of Prop
	(properties
		view 527
		priority 13
		signal fixPriOn
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward)
	)
)

(instance fCPanel of Prop
	(properties
		y 4
		x 156
		view 27
		loop 3
		priority 12
		signal fixPriOn
	)
	
	(method (doit)
		(if local0 (-- local0))
		(super doit:)
	)
	
	(method (setCycle theCycler)
		(if (and argc theCycler (theCycler isKindOf: BegLoop))
			(missileSelector hide:)
		)
		(super setCycle: theCycler &rest)
	)
)

(instance wheel of View
	(properties
		y 163
		x 158
		view 127
		loop 8
		cel 6
		priority 15
		signal (| stopUpdOn fixPriOn staticView)
	)
)

(instance swEndLoop of EndLoop
	(properties)
)

(instance vSonarScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(vSLine1 y: 46 show: setCycle: topScanCycle)
				(vSLine2 y: 46 show: setCycle: bottomScanCycle)
				(= cycles 2)
			)
			(1
				(vSLine1 setMotion: vS1MoveTo (vSLine1 x?) 36)
				(vSLine2 setMotion: vS2MoveTo (vSLine2 x?) 56 self)
			)
			(2
				(vSLine1 hide:)
				(vSLine2 hide:)
				(= seconds 4)
			)
			(3 (self init:))
		)
	)
)

(instance vS1MoveTo of MoveTo
	(properties)
)

(instance vS2MoveTo of MoveTo
	(properties)
)

(instance bottomScanCycle of Cycle
	(properties)
	
	(method (doit)
		(if (> (* (- (client y?) 46) 10) 7500)
			(client cel: 2)
		else
			(client cel: 1)
		)
	)
)

(instance topScanCycle of Cycle
	(properties)
	
	(method (doit)
		(if (> (* (- 46 (client y?)) 10) 35)
			(client cel: 3)
		else
			(client cel: 1)
		)
	)
)

(instance vSLine1 of Actor
	(properties
		y 46
		x 225
		view 27
		loop 2
		cel 1
		priority 13
		signal (| ignrAct stopUpdOn fixPriOn fixedLoop)
	)
)

(instance vSLine2 of Actor
	(properties
		y 46
		x 225
		view 27
		loop 2
		cel 1
		priority 13
		signal (| ignrAct stopUpdOn fixPriOn fixedLoop)
	)
)

(instance bankTurnInd of Actor
	(properties
		y 146
		x 156
		yStep 1
		view 227
		loop 1
		cel 4
		priority 12
		signal (| ignrAct stopUpdOn fixPriOn fixedLoop)
		cycleSpeed 4
		illegalBits $0000
		moveSpeed 4
	)
	
	(method (cue)
		(if (not (if cycler else mover)) (self stopUpd:))
	)
)

(instance diveLever of Actor
	(properties
		y 144
		x 277
		view 227
		loop 6
		cel 3
		priority 12
		signal fixPriOn
	)
)

(instance engineLever of Actor
	(properties
		y 177
		x 238
		view 127
		loop 9
		cel 2
		priority 13
		signal (| fixedLoop fixPriOn)
		moveSpeed 6
	)
)

(instance simulatedSubSimulatorSimulation of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 3)
			)
			(1
				(Print 27 1
					#at -1 5
					#dispose
				)
				(engineLever setMotion: MoveTo 231 172)
				(engSnd number: (SoundFX 54) play:)
				(= seconds 6)
			)
			(2
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(missileSelector show:)
				(= cycles 5)
			)
			(3
				(DisposeScript SHOWMAP)
				(curRoom newRoom: 39)
			)
		)
	)
)
