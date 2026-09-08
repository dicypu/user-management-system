import { useState } from 'react';
import UserCard from './components/UserCard';

export default function App() {
  // 1. Dinamik Kullanıcı Listesi State'i
  const [users, setUsers] = useState([
    { id: 1, ad: 'Emirhan', soyad: 'Yavuz', email: 'emirhan@thinx.com', telefon: '+905551234567', durum: true },
    { id: 2, ad: 'Ahmet', soyad: 'Demir', email: 'ahmet@thinx.com', telefon: '+905559876543', durum: false }
  ]);

  // 2. Basit Form State'i (Yeni Kullanıcı Ekleme Deneyimi)
  const [yeniAd, setYeniAd] = useState('');
  const [yeniSoyad, setYeniSoyad] = useState('');
  const [yeniEmail, setYeniEmail] = useState('');

  // 3. Kullanıcı Durumunu Değiştirme Fonksiyonu (State Immutability Kuralı)
  const durumTersineCevir = (id) => {
    setUsers(prevUsers =>
        prevUsers.map(user =>
            user.id === id ? { ...user, durum: !user.durum } : user
        )
    );
  };

  // 4. Yeni Kullanıcı Ekleme
  const kullaniciEkle = (e) => {
    e.preventDefault();
    if (!yeniAd.trim() || !yeniEmail.trim()) return;

    const yeniKullanici = {
      id: Date.now(),
      ad: yeniAd,
      soyad: yeniSoyad,
      email: yeniEmail,
      telefon: '+905550000000',
      durum: true
    };

    setUsers(prevUsers => [...prevUsers, yeniKullanici]);
    setYeniAd('');
    setYeniSoyad('');
    setYeniEmail('');
  };

  return (
      <div style={{ padding: '32px', fontFamily: 'Segoe UI, sans-serif', maxWidth: '800px', margin: '0 auto' }}>
        <header style={{ borderBottom: '2px solid #e2e8f0', paddingBottom: '16px', marginBottom: '24px' }}>
          <h1 style={{ margin: 0, color: '#0f172a' }}>THINX Kullanıcı Yönetimi Paneli</h1>
          <p style={{ margin: '6px 0 0 0', color: '#64748b' }}>Gün 11: Vite, React Components, Props ve useState Mimarisi</p>
        </header>

        {/* State Manipülasyon Formu */}
        <form onSubmit={kullaniciEkle} style={{ display: 'flex', gap: '8px', marginBottom: '24px', flexWrap: 'wrap' }}>
          <input
              type="text"
              placeholder="Ad"
              value={yeniAd}
              onChange={(e) => setYeniAd(e.target.value)}
              style={{ padding: '8px 12px', border: '1px solid #cbd5e1', borderRadius: '6px' }}
          />
          <input
              type="text"
              placeholder="Soyad"
              value={yeniSoyad}
              onChange={(e) => setYeniSoyad(e.target.value)}
              style={{ padding: '8px 12px', border: '1px solid #cbd5e1', borderRadius: '6px' }}
          />
          <input
              type="email"
              placeholder="E-posta"
              value={yeniEmail}
              onChange={(e) => setYeniEmail(e.target.value)}
              style={{ padding: '8px 12px', border: '1px solid #cbd5e1', borderRadius: '6px' }}
          />
          <button
              type="submit"
              style={{
                padding: '8px 16px',
                backgroundColor: '#0284c7',
                color: '#fff',
                border: 'none',
                borderRadius: '6px',
                cursor: 'pointer',
                fontWeight: '600'
              }}
          >
            Kullanıcı Ekle
          </button>
        </form>

        {/* Props ile Component'e Veri Aktarımı ve Listeleme */}
        <main>
          <h2 style={{ fontSize: '18px', color: '#334155' }}>Mevcut Kullanıcılar ({users.length})</h2>
          {users.map(user => (
              <div key={user.id} style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
                <UserCard
                    ad={user.ad}
                    soyad={user.soyad}
                    email={user.email}
                    telefon={user.telefon}
                    durum={user.durum}
                />
                <button
                    onClick={() => durumTersineCevir(user.id)}
                    style={{
                      padding: '6px 12px',
                      backgroundColor: '#f8fafc',
                      border: '1px solid #cbd5e1',
                      borderRadius: '6px',
                      cursor: 'pointer',
                      fontSize: '13px'
                    }}
                >
                  Durum Değiştir
                </button>
              </div>
          ))}
        </main>
      </div>
  );
}