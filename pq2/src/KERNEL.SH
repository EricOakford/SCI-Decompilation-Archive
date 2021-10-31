;;; Sierra Script 1.0 - (do not remove this comment)
;KERNEL.SH
;Definitions of the kernel interface for the Script interpreter.

(define	kernel	$ffff)


;;;(extern
;;;	;Resource handling.
;;;	Load					kernel	00
;;;	UnLoad					kernel	01
;;;	ScriptID				kernel	02
;;;	DisposeScript			kernel	03
;;;
;;;	;Object management.
;;;	Clone					kernel	04
;;;	DisposeClone			kernel	05
;;;	IsObject				kernel	06
;;;	RespondsTo				kernel	07
;;;
;;;	;Pictures.
;;;	DrawPic					kernel	08
;;;	Show					kernel	09
;;;	PicNotValid				kernel	10
;;;
;;;	;Animated objects & views.
;;;	Animate					kernel	11
;;;	SetNowSeen				kernel	12
;;;	NumLoops				kernel	13
;;;	NumCels					kernel	14
;;;	CelWide					kernel	15
;;;	CelHigh					kernel	16
;;;	DrawCel					kernel	17
;;;	AddToPic				kernel	18
;;;
;;;	;Window/dialog/controls.
;;;	NewWindow				kernel	19
;;;	GetPort					kernel	20
;;;	SetPort					kernel	21
;;;	DisposeWindow			kernel	22
;;;	DrawControl				kernel	23
;;;	HiliteControl			kernel	24
;;;	EditControl				kernel	25
;;;
;;;	;Screen text.
;;;	TextSize				kernel	26
;;;	Display					kernel	27
;;;
;;;	;Events.
;;;	GetEvent				kernel	28
;;;	GlobalToLocal			kernel	29
;;;	LocalToGlobal			kernel	30
;;;	MapKeyToDir				kernel	31
;;;
;;;	;Menu bar & status line.
;;;	DrawMenuBar				kernel	32
;;;	MenuSelect				kernel	33
;;;	AddMenu					kernel	34
;;;	DrawStatus				kernel	35
;;;
;;;	;Parsing.
;;;	Parse					kernel	36
;;;	Said					kernel	37
;;;	SetSynonyms				kernel	38
;;;
;;;	;Mouse functions.
;;;	HaveMouse				kernel	39
;;;	SetCursor				kernel	40
;;;
;;;	;File handling.
;;;	FOpen					kernel	41
;;;	FPuts					kernel	42
;;;	FGets					kernel	43
;;;	FClose					kernel	44
;;;
;;;	;Save/restore/restart.
;;;	SaveGame				kernel	45
;;;	RestoreGame				kernel	46
;;;	RestartGame				kernel	47
;;;	GameIsRestarting		kernel	48
;;;
;;;	;Sounds.
;;;	DoSound					kernel	49
		(enum
			InitSound
			PlaySound
			NextSound
			KillSound
			SoundOn
			StopSound
			PauseSound
			RestoreSound
			ChangeVolume
			ChangeSndState
			FadeSound
			NumVoices
		)
;;;
;;;	;List handling.
;;;	NewList					kernel	50
;;;	DisposeList				kernel	51
;;;	NewNode					kernel	52
;;;	FirstNode				kernel	53
;;;	LastNode				kernel	54
;;;	EmptyList				kernel	55
;;;	NextNode				kernel	56
;;;	PrevNode				kernel	57
;;;	NodeValue				kernel	58
;;;	AddAfter				kernel	59
;;;	AddToFront				kernel	60
;;;	AddToEnd				kernel	61
;;;	FindKey					kernel	62
;;;	DeleteKey				kernel	63
;;;
;;;	;Mathematical functions.
;;;	Random					kernel	64
;;;	Abs						kernel	65
;;;	Sqrt					kernel	66
;;;	GetAngle				kernel	67
;;;	GetDistance				kernel	68
;;;
;;;	;Miscellaneous.
;;;	Wait					kernel	69
;;;	GetTime					kernel	70
;;;
;;;	;String handling.
;;;	StrEnd					kernel	71
;;;	StrCat					kernel	72
;;;	StrCmp					kernel	73
;;;	StrLen					kernel	74
;;;	StrCpy					kernel	75
;;;	Format					kernel	76
;;;	GetFarText				kernel	77
;;;	ReadNumber				kernel	78
;;;
;;;	;Actor motion support.
;;;	BaseSetter				kernel	79
;;;	DirLoop					kernel	80
;;;	CanBeHere				kernel	81
;;;	OnControl				kernel	82
;;;	InitBresen				kernel	83
;;;	DoBresen				kernel	84
;;;	DoAvoider				kernel	85
;;;	SetJump					kernel	86
;;;
;;;	;Debugging support.
;;;	SetDebug				kernel	87
;;;	InspectObj				kernel	88
;;;	ShowSends				kernel	89
;;;	ShowObjs				kernel	90
;;;	ShowFree				kernel	91
;;;	MemoryInfo				kernel	92
		(enum
			LargestPtr
			FreeHeap
			LargestHandle
			FreeHunk
		)
;;;	StackUsage				kernel	93
		(enum
			MStackSize
			MStackMax
			MStackCur
			PStackSize
			PStackMax
			PStackCur
		)
;;;	Profiler				kernel	94
		(enum
			PRO_OPEN
			PRO_CLOSE
			PRO_ON
			PRO_OFF
			PRO_CLEAR
			PRO_REPORT
			TRACE_ON
			TRACE_OFF
			TRACE_RPT
		)
;;;	GetMenu					kernel	95
;;;	SetMenu					kernel	96
;;;
;;;	GetSaveFiles			kernel	97
;;;	GetCWD					kernel	98
;;;	CheckFreeSpace			kernel	99
;;;	ValidPath				kernel	100
;;;
;;;	CoordPri				kernel	101
;;;	StrAt					kernel	102
;;;	DeviceInfo				kernel	103
		(enum
			GetDevice
			CurDevice
			SameDevice
			DevRemovable
			CloseDevice
		)
;;;	GetSaveDir				kernel	104
;;;	CheckSaveGame			kernel	105
;;;
;;;	ShakeScreen				kernel	106
; shakes [dir]
		(enum	1
			shakeSDown
			shakeSRight
			shakeSDiagonal
		)


;;;	FlushResources			kernel	107
;;;	
;;;	SinMult					kernel	108
;;;	CosMult					kernel	109
;;;	SinDiv					kernel	110
;;;	CosDiv					kernel	111
;;;
;;;	Graph					kernel	112
		(enum	1			; ARGS								RETURNS
			GLoadBits		; bitmap number
			GDetect			; none 								# of colors available
			GSetPalette		; Palette number
			GDrawLine  		; x1/y1/x2/y2 mapSet colors...
			GFillArea  		; x/y/ mapSet colors...
			GDrawBrush 		; x/y/ size randomSeed mapSet colors...
			GSaveBits		; top/left/bottom/right mapSet		saveID of area
			GRestoreBits	; saveID from SaveBits
			GEraseRect		; top/left/bottom/right (draws visual in background color)
			GPaintRect		; top/left/bottom/right (draws visual in foreground color)
			GFillRect  		; top/left/bottom/right mapSet colors...
			GShowBits		; top/left/bottom/right mapSet
			GReAnimate		; top/left/bottom/right
			GInitPri		; horizon/base, Rebuild priority tables
		)

;;;	Joystick				kernel	113
		(enum	12
			JoyRepeat
		)
;;;)