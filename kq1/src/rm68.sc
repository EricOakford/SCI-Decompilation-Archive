;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm68 0
)

(local
	local0
	local1
	local2
	local3
)
(procedure (DwarfSteals item points)
	(return
		(if (ego has: item)
			(ego put: item)
			(theGame changeScore: (- 0 points))
			(Print 68 7)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

(procedure (SmallInvisibleGraham)
	(if (Btst fInvisible)
		(ego view: 42)
	else
		(ego view: 56)
	)
)

(instance rm68 of Room
	(properties
		picture 68
		horizon 59
		north 69
		south 67
	)
	
	(method (init)
		(LoadMany VIEW 4 7 56 66 136)
		(if (ego has: iMagicRing)
			(LoadMany VIEW 38 39)
		)
		(Load SCRIPT 613)
		(Load SOUND 17)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego posn: 43 45 setLoop: 2 cel: 0)
			)
			(south
				(ego posn: 80 187)
			)
			(else
				(ego posn: 196 115)
			)
		)
		(ego init:)
		(NormalEgo)
		(if (== prevRoomNum north)
			(self setScript: fadeInRoom)
		)
		(if (not (Btst fMuggedInCave))
			(if (not (Btst fDwarfInCave))
				(cond 
					((< (Random 1 100) 51)
						(Bclr fFlag29)
						(Bclr fFlag30)
					)
					((< (Random 1 100) 51)
						(Bset fFlag29)
						(Bclr fFlag30)
					)
					(else
						(Bset fFlag30)
						(Bclr fFlag29)
					)
				)
				(Bset fDwarfInCave)
			)
			(dwarf view: 136 setCycle: Walk init: hide:)
		)
		(cave1 init:)
		(cave2 init:)
	)
	
	(method (doit &tmp nRoom)
		(if
			(and
				(< (ego y?) 96)
				(< (ego x?) 172)
				(not (Btst fInvisible))
			)
			(= local2 1)
		)
		(if
			(and
				(>= (ego y?) 96)
				(< (ego x?) 247)
				(not (Btst fInvisible))
			)
			(= local3 1)
		)
		(cond 
			(script
				(script doit:)
			)
			(
				(and
					(Btst fFlag29)
					(not (Btst fMuggedInCave))
					(== (ego onControl: origin) cLGREEN)
					(not (Btst fInvisible))
					(>= (ego y?) 95)
					local3
				)
				(self setScript: takeWhatYouCan)
			)
			(
				(and
					(Btst fFlag29)
					(not (Btst fMuggedInCave))
					(== (ego onControl: origin) cLGREEN)
					(not (Btst fInvisible))
					(< (ego y?) 96)
					local2
				)
				(self setScript: takeWhatYouCan)
			)
			((== (ego onControl: origin) cCYAN)
				(self setScript: (ScriptID 613 0))
			)
			((== (ego onControl: origin) cRED)
				(self setScript: (ScriptID 613 1))
			)
			((== (ego onControl: origin) cGREY)
				(self setScript: (ScriptID 613 2))
			)
			((== (ego onControl: origin) cBLUE)
				(self setScript: (ScriptID 613 3))
			)
			((== (ego onControl: origin) cGREEN)
				(self setScript: (ScriptID 613 4))
			)
			((== (ego onControl: origin) cYELLOW)
				(self setScript: (ScriptID 613 6))
			)
			((== (ego onControl: origin) cBROWN)
				(self setScript: fadeOut)
			)
			((== (ego onControl: origin) cMAGENTA)
				(self setScript: fadeIn)
			)
			((== (ego onControl: origin) cLGREY)
				(self setScript: fadeOutRoom)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(if (== (ego edgeHit?) SOUTH)
					(Bclr fDwarfInCave)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event)
				(return)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(& (OnControl CMAP (event x?) (event y?)) (| cLGREEN cLCYAN))
					(User canInput:)
				)
				(event claimed: TRUE)
				(Print 68 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,cave]')
						(Print 68 1)
					)
					((Said '/catwalk,path')
						(Print 68 0)
					)
					((Said '/dwarf,man')
						(Print 68 2)
					)
				)
			)
		)
	)
)

(instance fadeOutRoom of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SmallInvisibleGraham)
				(ego
					setLoop: 1
					illegalBits: 0
					cel: 0
					setMotion: MoveTo (ego x?) 60
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego posn: 49 60)
				(= seconds 2)
			)
			(2
				(HandsOn)
				(self dispose:)
				(curRoom newRoom: 69)
			)
		)
	)
)

(instance fadeOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 0)
				(= local3 0)
				(SmallInvisibleGraham)
				(ego
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) 77 self
				)
			)
			(1
				(NormalEgo)
				(ego loop: 1 cel: 0 setMotion: MoveTo 237 77 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance fadeInRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SmallInvisibleGraham)
				(ego
					setLoop: 0
					cel: 0
					setCycle: 0
					setMotion: MoveTo 45 57 self
				)
			)
			(1
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(2
				(NormalEgo)
				(ego loop: 2 cel: 0 setMotion: MoveTo 45 69 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance fadeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 0)
				(= local3 0)
				(ego setMotion: MoveTo 300 (ego y?) self)
			)
			(1
				(SmallInvisibleGraham)
				(ego
					setLoop: 0
					cel: 0
					setCycle: 0
					setMotion: MoveTo 300 95 self
				)
			)
			(2
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(NormalEgo)
				(ego loop: 2 cel: 0 setMotion: MoveTo 300 114 self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance takeWhatYouCan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 93 loop: 1 init: play:)
				(Print 68 3)
				(dwarf show:)
				(if (= local1 (< (ego y?) 96))
					(dwarf posn: 243 74 setMotion: MoveTo 189 74 self)
				else
					(dwarf posn: 308 108 setMotion: MoveTo 288 119 self)
				)
			)
			(1
				(if local1
					(self cue:)
				else
					(dwarf setMotion: MoveTo 264 121 self)
				)
			)
			(2
				(cond 
					(haloTimer
						(Print 68 4)
					)
					((Btst fInvisible)
						(Print 68 5)
					)
					((DwarfSteals iMagicShield 8))
					((DwarfSteals iMagicMirror 8))
					((DwarfSteals iChest 8))
					((and (Btst fOpenedPouch) (DwarfSteals iPouch 6)))
					((DwarfSteals iPouch 3))
					((and (Btst fOpenedWalnut) (DwarfSteals iWalnut 6)))
					((DwarfSteals iGoldEgg 6))
					((DwarfSteals iSceptre 6))
					(else
						(Print 68 6)
					)
				)
				(= cycles 10)
			)
			(3
				(dwarf loop: 3 cel: 0)
				(= cycles 2)
			)
			(4 (dwarf setCycle: EndLoop self))
			(5
				((ScriptID 0 21) fade:)
				(Bset fMuggedInCave)
				(dwarf dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance dwarf of Actor)

(instance cave1 of NewFeature
	(properties
		x 49
		y 38
		noun 'cave'
		nsTop 10
		nsLeft 26
		nsBottom 66
		nsRight 73
		description {cave}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can't see much of anything in the dark cave.}
	)
)

(instance cave2 of NewFeature
	(properties
		x 293
		y 79
		noun 'cave'
		nsTop 48
		nsLeft 269
		nsBottom 111
		nsRight 318
		description {cave}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can't see much of anything in the dark cave.}
	)
)
