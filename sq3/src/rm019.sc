;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm019 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	[local6 49]
	[local55 50]
	[local105 50]
	local155
)
(procedure (localproc_0874 message theColor)
	(if (< numColors 16) (= theColor vWHITE))
	(= local1
		(Display message
			p_width 135
			p_mode teJustCenter
			p_at 168 12
			p_color theColor
			p_font 600
			p_save
		)
	)
)

(procedure (localproc_08a5 message)
	(Display 19 1 p_restore message)
	(RedrawCast)
)

(instance rm019 of Room
	(properties
		picture 19
		picAngle 1
	)
	
	(method (init &tmp [temp0 50])
		(HandsOff)
		(User mapKeyToDir: dirStop)
		(self setRegions: TRAVEL)
		(HandsOff)
		(if (> numColors 4)
			(Load PICTURE 191)
		else
			(Load PICTURE 300)
		)
		(Load VIEW 49)
		(Load VIEW 50)
		(Load VIEW 212)
		(Load SOUND 25)
		(super init:)
		(if
		(and (== currentSector 75) (not selectedSector))
			(= global161 3)
			(= gGEgoY_5 7)
			(= global163 3)
			(= global164 7)
			(= scanningSector 75)
		)
		(controls
			add: scanBut courseBut returnBut
			eachElementDo: #init
			draw:
		)
		(self setScript: rmScript)
		(scanner play:)
	)
	
	(method (doit)
		(if global167 (curRoom newRoom: 17))
		(super doit:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
	)
	
	(method (newRoom newRoomNumber)
		(scanner stop:)
		(HandsOn)
		(User mapKeyToDir: dirN)
		(timers eachElementDo: #dispose 84)
		(if local2 (localproc_08a5 local2) (= local2 0))
		(if local1 (localproc_08a5 local1) (= local1 0))
		(if local3 (localproc_08a5 local3) (= local3 0))
		(if local4 (localproc_08a5 local4) (= local4 0))
		(super newRoom: newRoomNumber)
	)
)

(instance rmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(scanBut state: 0 draw:)
				(courseBut state: 0 draw:)
				(returnBut state: 0 draw:)
				(= cycles 6)
			)
			(1
				(if (> numColors 4)
					(curRoom overlay: 191 1)
				else
					(curRoom overlay: 300 1)
				)
				(= cycles 2)
			)
			(2
				(= local2
					(Display
						19
						0
						dsWIDTH
						135
						dsALIGN
						1
						dsCOORD
						168
						3
						dsCOLOR
						14
						dsFONT
						600
						dsSAVEPIXELS
					)
				)
				(scanBut state: 1 draw:)
				(courseBut draw:)
				(returnBut state: 1 draw:)
				(scanBox init:)
				(you init:)
				(= cycles 2)
			)
			(3
				(if selectedSector
					(= local2
						(Display
							(Format @local55 {DESTINATION: SECTOR %d} selectedSector)
							dsWIDTH
							135
							dsALIGN
							1
							dsCOORD
							168
							3
							dsCOLOR
							9
							dsFONT
							600
							dsSAVEPIXELS
						)
					)
				)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance scanScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(if local2 (localproc_08a5 local2) (= local2 0))
				(cond 
					(
						(and
							(!= scanningSector 39)
							(!= scanningSector 62)
							(!= scanningSector 82)
							(!= scanningSector 69)
						)
						(self changeState: 2)
					)
					(
					(and (== scanningSector 69) (not forceBeamDestroyed)) (self changeState: 2))
					(local0 (= local0 0) (self changeState: 2))
					(else
						(if
							(!=
								local5
								(Format @local105 {SCANNING SECTOR %d} scanningSector)
							)
							(= local5
								(Format @local105 {SCANNING SECTOR %d} scanningSector)
							)
							(if local1 (localproc_08a5 local1) (= local1 0))
						)
						(scanBox setCycle: Forward)
						(scanBut state: 0 cel: 2 draw:)
						(returnBut state: 0 cel: 1 draw:)
						(= cycles 10)
					)
				)
			)
			(1
				(localproc_0874
					(Format @local105 {SCANNING SECTOR %d} scanningSector)
					14
				)
				(curRoom setScript: zoomScript)
			)
			(2 (= cycles 1))
			(3
				(if (< scanningSector 108)
					(++ scanningSector)
				else
					(= scanningSector 1)
				)
				(= global164 (/ scanningSector 12))
				(if
				(> (= global163 (- scanningSector (* global164 12))) 0)
					(++ global164)
				else
					(= global163 12)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance zoomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(scanBut cel: 2 state: 0 draw:)
				(returnBut cel: 1 state: 0 draw:)
				(courseBut cel: 1 state: 0 draw:)
				(scanBox
					setLoop:
						(cond 
							((== scanningSector 82) 1)
							((== scanningSector 39) 2)
							((== scanningSector 62) 3)
							((== scanningSector 69) 5)
						)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(you hide:)
			)
			(1
				(returnBut cel: 0 state: 1 draw:)
				(courseBut cel: 0 state: 1 draw:)
				(scanBut cel: 1 state: 1 draw:)
				(Timer setCycle: self 2)
			)
			(2
				(if local1 (localproc_08a5 local1) (= local1 0))
				(= saveDisabled TRUE)
				(= local3
					(Display
						(cond 
							((== scanningSector 39)
								{NAME:\n PLANET PHLEEBHUT\nSECTOR: 39\n\nLIGHT ATMOSPHERE\n1 KNOWN SETTLEMENT}
							)
							((== scanningSector 62)
								{NAME:\n MONOLITH BURGER FAST FOOD DIVE\nSECTOR: 62\n\nA FINITE\nNUMBER SERVED}
							)
							((== scanningSector 69)
								{NAME:\n PESTULON\nSECTOR: 69\nHABITANTS: UNKNOWN\nSURFACE UNCHARTED.\nIT FIGURES...}
							)
							((== scanningSector 82)
								{NAME:\n PLANET ORTEGA\nSECTOR: 82\nHABITANTS: UNKNOWN\nVOLCANIC CRATER-STREWN\nSURFACE}
							)
						)
						dsWIDTH
						(if (< global163 7) 220 else 91)
						dsCOORD
						(if (< global163 7)
							(+ (* (- global163 1) 25) 62)
						else
							(- (* (- global163 1) 25) 111)
						)
						(+ (* (- global164 1) 18) 6)
						dsFONT
						600
						dsCOLOR
						12
						dsSAVEPIXELS
					)
				)
			)
			(3
				(scanBox setCycle: BegLoop self)
				(if local3 (localproc_08a5 local3) (= local3 0))
				(= saveDisabled 0)
			)
			(4
				(scanBox setLoop: 0)
				(= local0 1)
				(curRoom setScript: scanScript)
				(you show:)
			)
		)
	)
)

(instance courseScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 49])
		(switch (= state newState)
			(0
				(if local2 (localproc_08a5 local2) (= local2 0))
				(= gGEgoY_5 (/ currentSector 12))
				(if
				(> (= global161 (- currentSector (* gGEgoY_5 12))) 0)
					(++ gGEgoY_5)
				else
					(= global161 12)
				)
				(if (> global163 global161) (= global165 1))
				(if (< global163 global161) (= global165 -1))
				(if (== global163 global161) (= global165 0))
				(if (> global164 gGEgoY_5) (= global166 1))
				(if (< global164 gGEgoY_5) (= global166 -1))
				(if (== global164 gGEgoY_5) (= global166 0))
				(= seconds 2)
			)
			(1
				(= temp0
					(if (== scanningSector currentSector)
						{COURSE ALREADY ACHIEVED}
					else
						{STANDBY\nCALCULATING COURSE}
					)
				)
				(if (!= scanningSector currentSector)
					(= selectedSector scanningSector)
				)
				(if local1 (localproc_08a5 local1) (= local1 0))
				(= local4
					(Display
						temp0
						dsWIDTH
						135
						dsALIGN
						1
						dsCOORD
						168
						3
						dsCOLOR
						14
						dsFONT
						600
						dsSAVEPIXELS
					)
				)
				(if (== temp0 {COURSE ALREADY ACHIEVED})
					(scanBut state: 0)
					(curRoom newRoom: 17)
				)
				(= seconds 3)
			)
			(2
				(if local4 (localproc_08a5 local4) (= local4 0))
				(if (!= scanningSector currentSector) (= cycles 2))
			)
			(3
				(= local4
					(Display
						{COURSE LOCKED}
						dsWIDTH
						135
						dsALIGN
						1
						dsCOORD
						168
						3
						dsCOLOR
						9
						dsFONT
						600
						dsSAVEPIXELS
					)
				)
				(= seconds 3)
			)
			(4
				(if local4 (localproc_08a5 local4) (= local4 0))
				(= cycles 1)
			)
			(5
				(localproc_08a5
					(Format @local55 {DESTINATION: SECTOR %d} selectedSector)
				)
				(= global220 local155)
				(curRoom newRoom: 17)
			)
		)
	)
)

(instance scanBut of DIcon
	(properties
		state $0001
		nsTop 3
		nsLeft 7
		key 49
	)
	
	(method (init)
		(super init:)
		(= view (if (> numColors 4) 49 else 149))
	)
	
	(method (doit)
		(self cel: 3 state: 0 draw:)
		(courseBut state: 0 cel: 1 draw:)
		(returnBut state: 0 cel: 1 draw:)
		(if (< (scanBox cel?) 2)
			(scanBox startUpd: setLoop: 0 setCel: 0 setCycle: 0)
			(= local155 global220)
			(= global220 0)
			(curRoom setScript: scanScript)
		else
			(zoomScript changeState: 3)
		)
	)
)

(instance courseBut of DIcon
	(properties
		nsTop 3
		nsLeft 60
		key 50
		loop 3
		cel 1
	)
	
	(method (init)
		(super init:)
		(= view (if (> numColors 4) 49 else 149))
	)
	
	(method (doit)
		(if (!= scanningSector currentSector)
			(self cel: 2 state: 0 draw:)
			(scanBut state: 0 cel: 2 draw:)
			(returnBut state: 0 cel: 1 draw:)
		)
		(curRoom setScript: courseScript)
	)
)

(instance returnBut of DIcon
	(properties
		state $0001
		nsTop 3
		nsLeft 113
		key 51
		loop 4
	)
	
	(method (init)
		(super init:)
		(= view (if (> numColors 4) 49 else 149))
	)
	
	(method (doit)
		(self cel: 2 state: 0 draw:)
		(courseBut state: 0 cel: 1 draw:)
		(scanBut state: 0 cel: 2 draw:)
		(curRoom newRoom: 17)
	)
)

(instance scanBox of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 50
			posn: (+ (* (- global163 1) 25) 22) (+ (* (- global164 1) 18) 32)
			setPri: 13
			setCel: 0
			setLoop: 0
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(self
			posn: (+ (* (- global163 1) 25) 22) (+ (* (- global164 1) 18) 32)
		)
	)
)

(instance you of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 50
			posn: (+ (* (- global161 1) 25) 22) (+ (* (- gGEgoY_5 1) 18) 32)
			setPri: 14
			setLoop: 4
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(self
			posn: (+ (* (- global161 1) 25) 22) (+ (* (- gGEgoY_5 1) 18) 32)
		)
	)
)

(instance scanner of Sound
	(properties
		number 25
		priority 1
		loop -1
	)
)
