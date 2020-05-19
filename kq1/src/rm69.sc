;;; Sierra Script 1.0 - (do not remove this comment)
(script# 69)
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
	rm69 0
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
			(Print 69 7)
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

(instance rm69 of Room
	(properties
		picture 69
		horizon 20
		south 68
		west 59
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
			(west WIPERIGHT)
			(south WIPEUP)
		))
		(super init:)
		(switch prevRoomNum
			(south
				(ego posn: 189 187 setLoop: 0 cel: 0)
			)
			(west
				(ego loop: 0 posn: 1 39)
			)
			(else
				(ego posn: 150 130)
			)
		)
		(Bset fLittleEgo)
		(ego init:)
		(NormalEgo)
		(if (not (Btst fMuggedInCave))
			(if (not (Btst fDwarfInCave))
				(cond 
					((< (Random 1 100) 51)
						(Bclr fFlag29)
						(Bclr fFlag30)
					)
					((< (Random 1 100) 51)
						(Bset fFlag30)
						(Bclr fFlag29)
					)
					(else
						(Bset fFlag29)
						(Bclr fFlag30)
					)
				)
				(Bset fDwarfInCave)
			)
			(dwarf view: 136 setCycle: Walk init: hide:)
		)
		(cave init:)
	)
	
	(method (doit &tmp nRoom)
		(if
			(and
				(< (ego y?) 96)
				(>= (ego x?) 219)
				(not (Btst fInvisible))
			)
			(= local2 1)
		)
		(if
			(and
				(>= (ego y?) 96)
				(>= (ego x?) 136)
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
					(Btst fFlag30)
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
					(Btst fFlag30)
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
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(if (== (ego edgeHit?) WEST)
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
				(Print 69 0)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,cave]')
						(Print 69 1)
					)
					((Said '/catwalk,path')
						(Print 69 0)
					)
					((Said '/dwarf,man')
						(Print 69 2)
					)
				)
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
					illegalBits: 0
					setLoop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(ego posn: 153 57)
				(= seconds 4)
			)
			(2
				(NormalEgo)
				(ego
					loop: 0
					cel: 0
					setPri: 1
					setMotion: MoveTo 174 56 self
				)
			)
			(3
				(ego setPri: -1)
				(HandsOn)
				(self dispose:)
			)
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
				(ego setPri: 1 setMotion: MoveTo 155 57 self)
			)
			(1
				(SmallInvisibleGraham)
				(ego
					illegalBits: 0
					setLoop: 0
					cel: 0
					setCycle: 0
					setMotion: MoveTo 90 109 self
				)
			)
			(2
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(NormalEgo)
				(ego loop: 2 cel: 0 setMotion: MoveTo 90 114 self)
			)
			(4
				(ego setPri: -1)
				(HandsOn)
				(self dispose:)
			)
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
				(Print 69 3)
				(dwarf show:)
				(if (= local1 (< (ego y?) 95))
					(dwarf posn: 156 61 setMotion: MoveTo 201 52 self)
				else
					(dwarf posn: 94 106 setMotion: MoveTo 91 113 self)
				)
			)
			(1
				(if local1
					(self cue:)
				else
					(dwarf setMotion: MoveTo 107 125 self)
				)
			)
			(2
				(cond 
					(haloTimer
						(Print 69 4)
					)
					((Btst fInvisible)
						(Print 69 5)
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
						(Print 69 6)
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

(instance cave of NewFeature
	(properties
		x 94
		y 89
		noun 'cave'
		nsTop 68
		nsLeft 71
		nsBottom 111
		nsRight 117
		description {cave}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {You can't see much of anything in the dark cave.}
	)
)
